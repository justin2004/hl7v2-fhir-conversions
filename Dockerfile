FROM ghcr.io/openfaas/classic-watchdog:0.1.5 as watchdog

FROM debian:10
RUN apt-get update && apt-get install -y leiningen

RUN mkdir -p /home/app

COPY --from=watchdog /fwatchdog /usr/bin/fwatchdog
RUN chmod +x /usr/bin/fwatchdog

# Add non root user
RUN groupadd app && useradd app -g app
RUN chown app /home/app

WORKDIR /home/app

USER app

ADD . /home/app/
USER root
RUN chown -R app:app /home/app
USER app

# Populate example here - i.e. "cat", "sha512sum" or "node index.js"
ENV fprocess="/home/app/entry.sh"
# Set to true to see request in function logs
ENV write_debug="true"

EXPOSE 8080

HEALTHCHECK --interval=30s CMD [ -e /tmp/.lock ] || exit 1

CMD ["fwatchdog"]
