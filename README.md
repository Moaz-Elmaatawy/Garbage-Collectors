# Garbage-Collectors
In this Project, two of the known algorithms used in Garbage Collectors are implemented:
- Mark & Compact GC
- Copy GC

The input files to the program will be
- File heap.csv : this is a comma separated file with three columns. Each line represents
the information about a single allocated object. This object may be used or not used.
      <br>- object-identifier: a unique 6 digits identifier of the allocated objects.
      <br>- memory-start: the index of the first byte in heap memory representing this object
      <br>- memory-end: the index of the last byte in heap memory representing this object
- File roots.txt: this is a text file that lists object-identifiers that are currently in use. Any
object that can not be reached directly or indirectly from objects listed in this file should
be considered as a garbage. Each line in this file contains a single object-identifier.
- File pointers.csv: this file stores the dependencies between different objects. It is a
comma separated file with two columns
- parent-identifier: a unique identifier for the parent object
- child-identifier: a unique identifier for the child object referenced by the parent

The output of your program will be
- File new-heap.csv: this is a comma separated file with the same structure of the
heap.csv showing the new memory layout after running the garbage collector

How to Run:
The jar accepts four arguments:
- The first three arguments are the absolute paths of heap.csv, roots.txt, and
pointers.csv.
- The last argument is the absolute path in which the new-heap.csv file will be
saved to
