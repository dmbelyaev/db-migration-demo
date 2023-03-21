docker image build -t db-migration-demo-job ./database
docker tag db-migration-demo-job dbelyaev626/db-migration-demo-job:1.1.1
docker push dbelyaev626/db-migration-demo-job:1.1.1
