build:
	docker buildx build --platform=linux/amd64,linux/arm64 . -t ttl.sh/7cbe7cb2-3fd0-4527-9d45-ef0535a95477-backend:1h

push:
	docker push ttl.sh/7cbe7cb2-3fd0-4527-9d45-ef0535a95477-backend:1h