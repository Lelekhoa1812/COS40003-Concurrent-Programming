// title
title Week 3 – Round Robin (RR) – UML Flow

// nodes
arrivals [icon: inbox, color: blue]{
  label "Job Arrivals"
}

readyq [icon: list, color: purple]{
  label "Ready Queue (Circular)"
  property "Equal priority for all jobs"
}

quantum [icon: timer, color: pink]{
  label "Time Quantum"
  config "q ms"
}

dispatcher [icon: share-2, color: gray]{
  label "Dispatcher"
  action "Assign CPU for q or until block/finish"
}

cpu [icon: cpu, color: orange]{
  label "CPU"
}

io [icon: hard-drive, color: teal]{
  label "I/O Subsystem"
}

completion [icon: flag, color: green]{
  label "Completion"
}

// flow
arrivals > readyq
readyq > dispatcher
quantum > dispatcher
dispatcher > cpu
cpu > completion
cpu > io
// if not finished on quantum expiry: requeue
cpu > readyq

// notes
facts [icon: info, color: gray]{
  pros "Good responsiveness; simple and fair"
  cons "Too small q → high overhead; too large q → FCFS-like"
  tuning "Choose q ~ 10–100ms for interactivity"
}
