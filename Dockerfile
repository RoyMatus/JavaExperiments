FROM rodolpheche/wiremock
CMD docker run -it --rm -p 8443:8443 rodolpheche/wiremock --https-port 8443 --verbose