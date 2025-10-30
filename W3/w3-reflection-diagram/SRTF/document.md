// title
title Week 3 – SRTF (Shortest Remaining Time First) – UML Flow

// nodes
arrivals [icon: inbox, color: blue]{
  label "Job Arrivals"
  note "New jobs may trigger preemption"
}

remaining_calc [icon: calculator, color: indigo]{
  label "Remaining Time Calculator"
  data "Tracks remaining CPU time per job"
}

ready_heap [icon: list, color: red]{
  label "Ready Structure (min-heap by remaining time)"
  policy "Preemptive"
}

preempt_decider [icon: alert-triangle, color: pink]{
  label "Preemption Decision"
  rule "If new job has less remaining time, preempt CPU"
}

dispatcher [icon: share-2, color: gray]{
  label "Dispatcher"
}

cpu [icon: cpu, color: orange]{
  label "CPU"
  behavior "Runs current shortest remaining job"
}

io [icon: hard-drive, color: purple]{
  label "I/O Subsystem"
}

completion [icon: flag, color: green]{
  label "Completion"
}

// flow
arrivals > remaining_calc
remaining_calc > ready_heap
ready_heap > preempt_decider
preempt_decider > dispatcher
dispatcher > cpu
cpu > completion
cpu > io
io > remaining_calc

// annotations
facts [icon: info, color: gray]{
  pros "Minimizes average waiting time vs. all policies"
  cons "High context-switch overhead; starvation of long jobs"
  preemption "Yes – on arrival or completion of I/O"
}
