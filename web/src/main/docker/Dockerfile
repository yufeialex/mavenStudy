FROM java:8
VOLUME /tmp
ADD studyMaven-web-*.war /app/app.war
RUN cd /app && jar -xvf app.war
RUN rm /app/app.war
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
ENV JAVA_OPTS=""
ENV SPRING_PROFILES_ACTIVE=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Dspring.profiles.active=${SPRING_PROFILES_ACTIVE:-docker} -cp /app -Djava.security.egd=file:/dev/./urandom org.springframework.boot.loader.WarLauncher" ]