// title
title Week 2 – Process State Transitions (UML State Overview)

// states
created [icon: plus-square, color: blue]{
  name string = "Created (Initial)"
}
ready [icon: fast-forward, color: green]{
  name string = "Ready"
}
running [icon: activity, color: orange]{
  name string = "Running"
}
blocked [icon: pause-circle, color: purple]{
  name string = "Blocked (I/O/Event)"
}
terminated [icon: x-circle, color: red]{
  name string = "Terminated (Final)"
}

// transitions (edges)
// Created -> Ready
created > ready
// Ready -> Running (dispatch)
ready > running
// Running -> Ready (preemption/deschedule)
running > ready
// Running -> Blocked (I/O or event wait)
running > blocked
// Blocked -> Ready (I/O complete)
blocked > ready
// Running -> Terminated (normal exit)
running > terminated
// (Optional) Any -> Terminated (killed)
ready > terminated
blocked > terminated
