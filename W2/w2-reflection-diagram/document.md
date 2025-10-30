// title
title Week 2 – Fork–Exec–Wait Sequence (UML Flow)

// actors/processes
parent [icon: user, color: blue]{
  name string = "Parent Process (P)"
  pid string = "PID = p"
  role string = "Spawns child; waits for completion"
}

child [icon: user, color: green]{
  name string = "Child Process (C)"
  pid string = "PID = c (new)"
  role string = "Executes new program"
}

progA [icon: file-code, color: gray]{
  name string = "Program A (Original Image)"
  note string = "Parent & child initially share image post-fork (copy-on-write)"
}

progB [icon: file-code, color: orange]{
  name string = "Program B (New Image)"
  note string = "Replaces child image via exec()"
}

// system calls (document nodes)
sc_fork [icon: git-branch, color: purple]{
  name string = "fork()"
  effect string = "Duplicate process; returns 0 to child, c>0 to parent"
}

sc_exec [icon: refresh-ccw, color: pink]{
  name string = "exec()"
  effect string = "Replace child’s image with Program B"
}

sc_wait [icon: hourglass, color: indigo]{
  name string = "wait()/waitpid()"
  effect string = "Parent blocks; retrieves child exit status"
}

sc_exit [icon: log-out, color: red]{
  name string = "exit(status)"
  effect string = "Child terminates; status collected by parent"
}

// flow (relationships)
parent > sc_fork
sc_fork > child
// after fork both have Program A image
progA < parent
progA < child

// child path: exec new program, then exit
child > sc_exec
sc_exec > progB
progB > sc_exit

// parent path: wait for child to finish, then resume
parent > sc_wait
sc_wait > child
sc_exit > sc_wait
