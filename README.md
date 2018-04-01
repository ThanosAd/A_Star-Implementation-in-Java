# A_Star-Implementation-in-Java

An implementation of the A* algorithm in Java.

The Project was created for the needs of the Artificial Intelligence course of the Department of Computer Science & Engineering, University of Ioannina, spring of 2017.

A.I course: http://www.cs.uoi.gr/~arly/courses/ai/


# A* Algorithm:

The specific algorithm is capable to calculate the path with the minimum cost between two nodes, minimizing the f(n) = g(n)+ h(n), where n is the last node on the path, g(n) is the cost of the path from the start node to n, and h(n) is a heuristic that estimates the cost of the cheapest path from n to the destination. 



# My Implementation:

The whole idea of this project is to create a random Maze filled with 1 and 0, where 1 presents ''wall'' and 0 presents unblocked road. Then, passing two nodes S and G ,(with S being the starting node and G the destination), the purpose is to find the path with the minimum cost between these two points, using A*. The diagonal movement is not permitted. Up/Down ovement has a cost of 1, where Left/Right movement has a cost of 0.5. I used the Manhattan Distance as the heuristic function.





