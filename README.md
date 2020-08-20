# postgres


to run newman:

docker-compose -f docker-compose-ci.yml up -d --force-recreate --build;
docker-compose -f docker-compose-ci.yml run wait -c server:8080 -t 120
docker exec  postgres_newman_1 newman run PRODUCT_TEST.postman_collection.json --reporters cli,junit,htmlextra --reporter-junit-export "newman/report.xml" --reporter-htmlextra-export "newman/report.html" 

stop newman:
docker-compose -f docker-compose-ci.yml down
