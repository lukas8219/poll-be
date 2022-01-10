create_queue(){
	sleep 10;

curl -i -u guest:guest -H "content-type:application/json" -XPUT -d '{"durable":true}' http://localhost:15672/api/queues/%2f/SEND_EMAIL_QUEUE;
curl -i -u guest:guest -H "content-type:application/json" -XPUT -d '{"password":"secret","tags":"administrator"}' http://localhost:15672/api/users/lucaspolesello;
}


docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3.9-management & create_queue


