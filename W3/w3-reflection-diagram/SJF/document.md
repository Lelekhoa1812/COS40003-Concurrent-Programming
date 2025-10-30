// title
title Week 3 – SJF (Shortest Job First) – UML Flow

// nodes
arrivals [icon: inbox, color: blue]{
  label "Job Arrivals"
  note "Jobs with estimated CPU burst"
}

selector [icon: filter, color: indigo]{
  label "Burst Estimator"
  method "History/exponential averaging"
}

readyq [icon: list, color: orange]{
  label "Ready Set (min-burst at head)"
  policy "Non-preemptive"
  decision "Pick shortest (expected) job"
}

dispatcher [icon: share-2, color: gray]{
  label "Dispatcher"
}

cpu [icon: cpu, color: orange]{
  label "CPU"
  behavior "Runs selected job to completion"
}

io [icon: hard-drive, color: purple]{
  label "I/O Subsystem"
}

completion [icon: flag, color: green]{
  label "Completion"
}

arrivals > selector
selector > readyq
readyq > dispatcher
dispatcher > cpu
cpu > completion
cpu > io
io > selector

// notes
facts [icon: info, color: gray]{
  pros "Optimal average waiting time (if estimates accurate)"
  cons "Requires burst prediction; starvation of long jobs possible"
  preemption "None"
}
