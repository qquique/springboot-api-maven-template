FROM maven:3-eclipse-temurin-21 AS maven-build
WORKDIR /opt/app

COPY src ./src
COPY pom.xml ./
RUN --mount=type=cache,target=/root/.m2 mvn package -DskipTests

FROM eclipse-temurin:21-alpine AS jre-build

WORKDIR /opt/app
COPY --from=maven-build /opt/app/target/jsbm-0.0.1-SNAPSHOT.jar ./
RUN jar xf jsbm-0.0.1-SNAPSHOT.jar
RUN jdeps --ignore-missing-deps -q  \
  --recursive  \
  --multi-release 21  \
  --print-module-deps  \
  --class-path 'BOOT-INF/lib/*'  \
  jsbm-0.0.1-SNAPSHOT.jar > deps.info
RUN jlink \
  --verbose \
  --add-modules $(cat deps.info) \
  --strip-debug \
  --compress 2 \
  --no-header-files \
  --no-man-pages \
  --output /customjre

FROM alpine:3.18
COPY --from=jre-build /customjre /opt/jre
ENV JAVA_HOME=/opt/jre
ENV PATH="$PATH:$JAVA_HOME/bin"

COPY --from=maven-build /opt/app/target/jsbm-0.0.1-SNAPSHOT.jar /opt/app.jar
ENTRYPOINT ["java", "-XX:+UseParallelGC", "-XX:MaxRAMPercentage=75", "-jar","/opt/app.jar"]
