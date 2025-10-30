// title
title Week 5 – Locks: Concepts, APIs, and Critical Section

// shared state & threads
shared_data [icon: database, color: purple]{
  name string = "Shared Data"
  examples string = "Counters, queues, maps"
}

thread_A [icon: activity, color: green]{ name string = "Thread A" }
thread_B [icon: activity, color: green]{ name string = "Thread B" }
thread_C [icon: activity, color: green]{ name string = "Thread C" }

// lock object & critical section
lock_obj [icon: lock, color: indigo]{
  name string = "Lock Object"
  guarantees string = "Mutual Exclusion (ME)"
}

critical [icon: shield, color: orange]{
  name string = "Critical Section"
  property string = "Atomic access to shared_data"
}

// Java mechanisms
sync_kw [icon: code, color: blue]{
  name string = "Java synchronized"
  effects string = "Mutual Exclusion + happens-before"
  scope string = "Methods / blocks"
}

reentrant [icon: key, color: teal]{
  name string = "ReentrantLock (Lock interface)"
  features string = "tryLock(), timed tryLock, lockInterruptibly(), unlock()"
}

// relationships
thread_A > lock_obj
thread_B > lock_obj
thread_C > lock_obj
lock_obj > critical
critical > shared_data


