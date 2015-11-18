FROM vpetersson/pandoc

RUN apt-get update && apt-get -y install groovy git

COPY . /groovy-pandoc

#RUN git clone https://github.com/dfrommi/groovy-pandoc.git /groovy-pandoc

WORKDIR /groovy-pandoc

# Pre-fetch gradle wrapper
RUN ./gradlew clean

ENTRYPOINT [ "./gradlew" ]
CMD [ "test", "integrationTest", "install" ]
