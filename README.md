# Binary Search Tree (BST) Implementation

This repository contains an implementation of a **Binary Search Tree (BST)** in Java. A binary search tree is a data structure that maintains a sorted order of elements, allowing efficient insertion, deletion, and search operations.

## Features
- Insertion of nodes into the tree
- Search for a node in the tree
- Deletion of nodes from the tree **THIS METHOD IS STILL NOT READY**
- Traversals: In-order, Pre-order, and Post-order
- Supports both recursive and iterative methods (if applicable)
- Handles cases of nodes with zero, one, or two children during deletion

## Table of Contents
1. [Introduction](#introduction)
2. [How it Works](#how-it-works)
3. [Getting Started](#getting-started)
4. [Methods](#methods)

## Introduction

A Binary Search Tree (BST) is a binary tree in which each node has at most two children, and the left child's value is always less than the parent's value, while the right child's value is always greater. This property makes BSTs efficient for searching, insertion, and deletion operations.

## How it Works

In this implementation:
- **Insertion**: Adds a node while maintaining the BST property.
- **Search**: Finds a node based on its value.
- **Deletion**: Removes a node, adjusting the tree as needed to maintain the BST property. **THIS METHOD IS STILL NOT READY**
- **Traversal**: Provides methods for traversing the tree in different orders (in-order, pre-order, post-order).

## Getting Started

To use this Binary Search Tree implementation, you need Java installed on your machine. You can download Java from [here](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html).

### Prerequisites

- Java 8 or higher.
- A text editor or IDE for writing Java code (e.g., Visual Studio Code, IntelliJ IDEA, Eclipse).

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/Ayham133/BinarySearchTree.git

### Methods

Here’s a brief overview of the methods implemented in the Binary Search Tree class:
- **`insert(int value)`**:Adds a value to the tree.
- **`delet(int target)`**:delte and return the target from this BST. **THIS METHOD IS STILL NOT READY**
- **`serach(int target)`**:Returns true if the target is found in this BST, false otherwise.
- **`inOrderTraversal(Node node)`**: Performs an in-order traversal and prints the node values in ascending order.
- **`preOrderTraversal(Node node)`**: Performs a pre-order traversal, visiting the node first, then the left child, and finally the right child.
- **`postOrderTraversal(Node node)`**: Performs a post-order traversal, visiting the left child first, then the right child, and finally the node itself.
- **`getRoot()`**: Returns the root node of the tree.
