// title
title Week 3 – FCFS (First Come, First Served) – UML Flow

// nodes
arrivals [icon: inbox, color: blue]{
  label "Job Arrivals"
  note "Jobs enter in arrival order"
}

readyq [icon: list, color: blue]{
  label "Ready Queue (FIFO)"
  policy "Non-preemptive"
  property "Head-of-line job selected"
}

dispatcher [icon: share-2, color: gray]{
  label "Dispatcher"
  action "Dequeue head; assign CPU"
}

cpu [icon: cpu, color: orange]{
  label "CPU"
  behavior "Runs until completion or block"
}

io [icon: hard-drive, color: purple]{
  label "I/O Subsystem"
  behavior "Blocks job then returns to Ready"
}

completion [icon: flag, color: green]{
  label "Completion"
  metric "Turnaround time"
}

// flow
arrivals > readyq
readyq > dispatcher
dispatcher > cpu
cpu > completion
cpu > io
io > readyq

// annotations
readyq ~ dispatcher
dispatcher ~ cpu

// characteristics
facts [icon: info, color: gray]{
  pros "Simple, minimal overhead"
  cons "Convoy effect: short jobs wait behind long jobs"
  fairness "Arrival-order fair; response may be poor"
}
