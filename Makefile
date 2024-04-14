DOCKER_DEFAULT_PLATFORM=linux/amd64

build:
	docker build . -t ttl.sh/7cbe7cb2-3fd0-4527-9d45-ef0535a95477:1h

push:
	docker push ttl.sh/7cbe7cb2-3fd0-4527-9d45-ef0535a95477:1h