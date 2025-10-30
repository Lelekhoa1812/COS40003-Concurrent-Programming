// title
title Threads Overview – Concepts & APIs

// process & shared state
process [icon: server, color: blue]{
  name string = "Process"
  address_space string = "Code, statics, heap"
  resources string = "Open files, sockets, locks"
}

shared_state [icon: share-2, color: purple]{
  name string = "Shared State"
  memory string = "Code/Statics/Heap"
  os_handles string = "FDs, sockets"
  sync string = "Locks/Monitors"
}

// thread & private state
thread [icon: activity, color: orange]{
  name string = "Thread"
  private_state string = "PC, SP, registers"
  stack string = "Per-thread stack"
  sched string = "Priority, state"
}

private_state [icon: shield, color: teal]{
  name string = "Private State"
  regs string = "PC/SP/Regs"
  stack string = "Call stack"
}

// models/patterns
patterns [icon: workflow, color: yellow]{
  name string = "Patterns"
  mngr_worker string = "Manager/Worker"
  pipeline string = "Pipeline (Producer–Consumer)"
}

// POSIX APIs
posix_api [icon: code, color: gray]{
  name string = "POSIX Threads"
  create string = "pthread_create()"
  join string = "pthread_join()"
  exit string = "pthread_exit()"
  yield string = "sched_yield()"
}

// Java APIs
java_api [icon: coffee, color: green]{
  name string = "Java Threads"
  creation string = "Thread / Runnable"
  coord string = "start(), join(), wait()/notify()"
  priority string = "setPriority()"
  deprecated string = "stop(), suspend(), resume()"
}

// safety & atomicity
safety [icon: alert-triangle, color: red]{
  name string = "Thread-Safety"
  not_safe string = "ArrayList, GUI"
  note string = "x++ is not atomic → sync/atomics"
}

// pools & executors
thread_pool [icon: layers, color: indigo]{
  name string = "Thread Pool"
  workers string = "Fixed workers"
  queue string = "Task queue (bounded)"
  behavior string = "Wait→Notify→Execute→Repeat"
}

executors [icon: play-circle, color: pink]{
  name string = "Executors (Java)"
  exec string = "execute()"
  shutdown string = "shutdown()"
  await string = "awaitTermination()"
}

// tuning & ops
tuning [icon: sliders, color: gray]{
  name string = "Tuning & Overload"
  size string = "Pool sizing"
  overload string = "Bounded queue, shedding"
  risk string = "Avoid starvation/thrash"
}

// pros/cons
pros [icon: thumbs-up, color: emerald]{
  name string = "Pros"
  p1 string = "Overlap I/O & compute"
  p2 string = "Cheaper than processes"
  p3 string = "Multi-CPU speed-up"
}

cons [icon: thumbs-down, color: rose]{
  name string = "Cons"
  c1 string = "Subtle concurrency bugs"
  c2 string = "Debugging complexity"
  c3 string = "Compatibility issues"
}

// relationships
thread > process
shared_state < process
private_state < thread

thread ~ shared_state
patterns > thread

posix_api > thread
java_api > thread

thread_pool > thread
executors > thread_pool
tuning ~ thread_pool

safety ~ java_api
pros ~ thread
cons ~ thread
