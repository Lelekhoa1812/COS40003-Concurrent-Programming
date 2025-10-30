// title
title Lock Internals and Implementation Pathways Data Model

// lock abstraction
lock_struct [icon: lock, color: blue]{
  id string
  name string // e.g., "lock_t"
  owner string // Holder thread (optional)
  waitq string // Queue for contenders
  goal string // ME, fairness, performance
}

// hardware primitives
tas [icon: cpu, color: purple]{
  id string
  name string // "Test-and-Set (TAS)"
  note string // "Spinlock"
}

cas [icon: cpu, color: orange]{
  id string
  name string // "Compare-and-Swap (CAS)"
  note string // "Stronger primitive"
}

faa [icon: cpu, color: orange]{
  id string
  name string // "Fetch-and-Add (FAA)"
  note string // "Ticket locks (fair)"
}

// strategies
spin [icon: rotate-ccw, color: teal]{
  id string
  name string // "Spinning"
  pros string // "Short holds"
  cons string // "Wastes CPU under preemption"
}

ticket [icon: list-ordered, color: green]{
  id string
  name string // "Ticket Lock"
  pros string // "FIFO fairness"
  cons string // "Still spins"
}

yielding [icon: skip-forward, color: purple]{
  id string
  name string // "Yield()"
  pros string // "Less CPU waste vs pure spin"
  cons string // "Many context switches"
}

queued [icon: queue, color: indigo]{
  id string
  name string // "Park/Unpark Queue"
  pros string // "Sleep waiters; deterministic handoff"
}

// java usage scope
inst_lock [icon: box, color: pink]{
  id string
  name string // "Instance Lock"
  note string // "synchronized(this)"
}

split_locks [icon: layout, color: pink]{
  id string
  name string // "Separate Locks"
  note string // "lock1/lock2 for independent data"
}

class_lock [icon: boxes, color: pink]{
  id string
  name string // "Class/Static Lock"
  note string // "synchronized(Class) or static ReentrantLock"
}

// relationships
lock_struct > tas.id
lock_struct > cas.id
lock_struct > faa.id

tas > spin.id
cas > spin.id
faa > ticket.id
ticket > queued.id
yielding > queued.id

inst_lock > split_locks.id
class_lock > lock_struct.id
