// title
title Week 4 – Producer–Consumer with Bounded Queue (Backpressure)

// components
source [icon: radio, color: blue]{
  name string = "External Source"
  emits string = "Events / Records"
}

producer [icon: upload, color: teal]{
  name string = "Producer Thread"
  role string = "Ingests from source; enqueues"
}

queue [icon: inbox, color: purple]{
  name string = "Bounded Queue"
  capacity string = "Fixed size (backpressure)"
  behavior string = "Blocks or rejects on full"
}

pool [icon: layers, color: indigo]{
  name string = "Worker Pool"
  size string = "Tuned to workload mix"
}

worker1 [icon: activity, color: green]{ name string = "Worker #1" }
worker2 [icon: activity, color: green]{ name string = "Worker #2" }
workerN [icon: activity, color: green]{ name string = "Worker #N" }

sink [icon: database, color: orange]{
  name string = "Sink"
  examples string = "DB / Filesystem / API"
}

ctrl [icon: settings, color: gray]{
  name string = "Lifecycle Controller"
  actions string = "Start/Stop; Graceful Shutdown"
}

metrics [icon: bar-chart-2, color: pink]{
  name string = "Metrics/Logs"
  observes string = "Queue depth, throughput, latency, errors"
}

// relationships
source > producer
producer > queue
queue > pool
pool > worker1
pool > worker2
pool > workerN
worker1 > sink
worker2 > sink
workerN > sink

ctrl ~ producer
ctrl ~ pool
metrics ~ queue
metrics ~ pool
metrics ~ sink
