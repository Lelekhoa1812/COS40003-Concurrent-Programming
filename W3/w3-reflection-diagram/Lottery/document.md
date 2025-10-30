// title
title Week 3 – Lottery Scheduling – UML Flow

// nodes
arrivals [icon: inbox, color: blue]{
  label "Job Arrivals"
  note "Jobs arrive with ticket counts"
}

ticket_bank [icon: gift, color: teal]{
  label "Ticket Pool"
  data "Sum of all tickets T"
}

rng [icon: shuffle, color: pink]{
  label "Random Draw"
  rule "Pick number r ∈ [1..T]"
}

selector [icon: target, color: indigo]{
  label "Winner Selection"
  method "Map r to owning job"
}

dispatcher [icon: share-2, color: gray]{
  label "Dispatcher"
}

cpu [icon: cpu, color: orange]{
  label "CPU"
}

io [icon: hard-drive, color: purple]{
  label "I/O Subsystem"
}

completion [icon: flag, color: green]{
  label "Completion"
}

// flow
arrivals > ticket_bank
ticket_bank > rng
rng > selector
selector > dispatcher
dispatcher > cpu
cpu > completion
cpu > io
io > ticket_bank

// notes
facts [icon: info, color: gray]{
  proportional "Expected CPU share ∝ ticket count"
  pros "Easy to add/remove jobs; no global per-job state"
  cons "Short-term variance; not deterministic"
}
