from prometheus_client import start_http_server, Gauge
from random import randrange
import time

foo = Gauge('foo', 'Explanation', ['label_name'])

if __name__ == '__main__':
    # Start up the server to expose the metrics.
    start_http_server(8000)
    while True:
        foo.labels(label_name="label_value").set(time.time())