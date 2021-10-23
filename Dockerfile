# User containerRegistry link of my office , so removed here.
FROM containerregistry
USER ROOT
WORKDIR /bookstore
RUN chmod 777 -R /bookstore && chown -R 99 /application
COPY target/bookstore-1.0-SNAPSHOT.jar
EXPOSE 8080
CMD java -jar bookstore-1.0-SNAPSHOT.jar com.hararoo.bookstore.Application