# postgres

#newman
to run newman:

docker-compose -f docker-compose-ci.yml up -d --force-recreate --build;
docker-compose -f docker-compose-ci.yml run wait -c server:8080 -t 120
docker exec  postgres_newman_1 newman run PRODUCT_TEST.postman_collection.json --reporters cli,junit,htmlextra --reporter-junit-export "newman/report.xml" --reporter-htmlextra-export "newman/report.html" 

stop newman:
docker-compose -f docker-compose-ci.yml down


push docker (no need to run)
 docker build . -t hansdson-postgres
 docker tag hansdson-postgres nivitzhaky/hansdson-postgres
 docker push nivitzhaky/hansdson-postgres
 
#openshift:

 oc login -u admin  -p admin
 oc project myproject
 git clone https://github.com/nivitzhaky/postgres.git
 cd postgres/src/main/resources/k8s/
 oc apply -f deployment-postgres.yml
 oc apply -f postgres-service.yml
 oc apply -f deployment.yml
 oc apply -f service.yml
 
#AWS 
 chmod 400 ~/Downloads/awstest.pem
 ssh -i ~/Downloads/awstest.pem ec2-user@ec2-34-234-204-3.compute-1.amazonaws.com
 
 sudo yum update -y
 sudo yum install -y docker
 sudo service docker start
 sudo yum install -y git
 git clone https://github.com/nivitzhaky/postgres.git
 cd postgres
 sudo curl -L https://github.com/docker/compose/releases/download/1.20.0/docker-compose-`uname -s`-`uname -m` -o /usr/local/bin/docker-compose
 sudo chmod +x /usr/local/bin/docker-compose
 docker-compose -f docker-compose-aws.yml up -d
