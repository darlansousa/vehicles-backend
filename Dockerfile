# BUILD
FROM java17-gradle-alpine as build
COPY build.gradle.kts .
COPY settings.gradle.kts .
COPY src src
RUN gradle build -x test --no-daemon --info
#
# RELEASE
#
FROM java17-graalvm-jdk as release
WORKDIR /app
COPY --from=build /build/libs/*.jar ./app.jar
COPY docker-entrypoint.sh ./
ENTRYPOINT ["/app/docker-entrypoint.sh"]
