FROM vpetersson/pandoc

RUN apt-get update && apt-get -y install groovy git

COPY . /groovy-pandoc

#RUN git clone https://github.com/dfrommi/groovy-pandoc.git /groovy-pandoc

# Update tp latest version of pandoc.
# TODO: use Ubuntu image directly or a texlive image (if tex is required)
RUN wgethttps://github.com/jgm/pandoc/releases/download/1.17.2/pandoc-1.17.2-1-amd64.deb && \
    dpkg -i pandoc* && \
    rm pandoc* && \
    apt-get clean

WORKDIR /groovy-pandoc

# Pre-fetch gradle wrapper
RUN ./gradlew clean

ENTRYPOINT [ "./gradlew" ]
CMD [ "test", "integrationTest", "install" ]
