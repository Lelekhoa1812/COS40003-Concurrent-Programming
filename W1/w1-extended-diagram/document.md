// title
title Concurrent Server Data Model

// define tables
server [icon: server, color: blue]{
  id string pk
  name string
  maxThreads int
}

thread_pool [icon: cpu, color: orange]{
  id string pk
  server_id string
  pool_size int
  status string
}

thread [icon: activity, color: green]{
  id string pk
  thread_pool_id string
  name string
  status string
}

request [icon: send, color: purple]{
  id string pk
  thread_id string
  request_time timestamp
  status string
  result string
}

// define relationships
thread_pool.server_id > server.id
thread.thread_pool_id > thread_pool.id
request.thread_id > thread.id
