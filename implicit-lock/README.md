---
layout: pattern
title: Implicit Lock
folder: implicit-lock
permalink: /patterns/implicit-lock/

categories: Concurrency
language: en
tags:
 - Enterprise Integration Pattern
---

## Intent

Implicit locks are automatically acquired to ensure data integrity among multiple users. 
To allow greatest concurrency, it acquires locks only when needed and frees them as soon as it can.
Maintaining the implicit lock eliminates the need to obtain an explicit lock each time a file is updated.

## Applicability
Use Implicit Lock pattern when

* You want to get up-to-date session data
* You want to avoid unknowingly writing over someone's changes
* You want to avoid allowing a business transaction to create inconsistent data

## Related Patterns

* Coarse Grained Lock 
* Optimistic Offline Lock 
* Pessimistic Offline Lock 
