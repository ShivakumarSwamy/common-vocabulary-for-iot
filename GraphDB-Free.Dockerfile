FROM busybox as bbx
ARG version=8.9.0
WORKDIR /app
COPY ./graphdb-free-${version}-dist.zip /app
RUN unzip /app/graphdb-free-${version}-dist.zip && \
    rm /app/graphdb-free-${version}-dist.zip

FROM openjdk:8-jre-slim
ARG version=8.9.0
ENV GRAPHDB_PARENT_DIR=/opt/graphdb
ENV GRAPHDB_HOME=${GRAPHDB_PARENT_DIR}/home
ENV GRAPHDB_INSTALL_DIR=${GRAPHDB_PARENT_DIR}/dist

#COPY graphdb-free-${version}-dist.zip /tmp
RUN mkdir -p ${GRAPHDB_PARENT_DIR} && \
    cd ${GRAPHDB_PARENT_DIR} && \
    mkdir -p ${GRAPHDB_HOME}
COPY --from=bbx /app/graphdb-free-${version} ${GRAPHDB_INSTALL_DIR}
ENV PATH=${GRAPHDB_INSTALL_DIR}/bin:$PATH
CMD ["-Dgraphdb.home=/opt/graphdb/home"]
ENTRYPOINT ["/opt/graphdb/dist/bin/graphdb"]
EXPOSE 7200