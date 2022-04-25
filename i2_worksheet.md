Iteration 2 Worksheet
=====================

Paying Off Technical Debt
-------------------------

1. Too tightly coupled with JSON Parser

Inadvertent and prudent technical debt. We coupled the json parser too tightly with the ModelManager classes under the assumption that we are strictly working under a JSON schema. We then had to implement an SQL database and since most of our architecture relied on a json schema it was more difficult and time consuming to implement an SQL schema. A fix is still in progress.

2. Having model classes coupled to android 

Inadvertent and prudent technical debt. We used android specific functions in the classes of our models, mainly the android specific Log function. This prevented us from testing our models without the dependency of the android api which made it more difficult to test and harder to debug why the tests failed. We fixed this by decoupling the model classes from android. Commit here.


SOLID Violation (for Group 14)
------------------------------

Violation: I – Interface Segregation Principle

Commits where this exists:
1. 2ec0e146
2. 59c92dfb
3. 4b2741b4

The classes ItemAccesser, InventoryManagerAccessor, and AccountAccesser all share a lot of similar methods whose class signatures should be enforced via interfaces.

https://code.cs.umanitoba.ca/winter-2022-a02/group-14/warehouse-inventory-system/-/issues/70


Retrospective
-------------

The retrospective allowed us to think critically about our entire project as everyone contributed their ideas to the retrospective. One of the things we mentioned in our retrospective was how we should be making a better plan before implementing any code as well as communicating with the related teams regarding how we want to sync our merges and stage fixes for each other. While there is no way to prove this since it mostly took place in voice calls, it did streamline the process of working as a group on the project, minimising the chance of any potential conflicts from emerging.


Design Patterns
---------------

The design pattern we chose was Adapter. In our project we currently have two adapters for our recycler views. One of them is for our topic recycler view and the other is for our discussion recycler view, both of which display a list of items. 

Link to the topic recycler view adapter: https://code.cs.umanitoba.ca/winter-2022-a02/group-15/simple-forum/-/blob/main/app/src/main/java/com/example/simple_forum/controller/adapters/TopicRecyclerAdapter.java

Link to the discussion recycler view adapter: https://code.cs.umanitoba.ca/winter-2022-a02/group-15/simple-forum/-/blob/main/app/src/main/java/com/example/simple_forum/ui/adapters/DiscussionRecyclerAdapter.java



Iteration 1 Feedback Fixes
--------------

The date and time fields in our domain specific objects required an API and was coupled with androids Log API. We have removed all android specific Log API’s from our model classes.

We also forgot to remove the default LoginActivity provided as a template from android as we never once used it and forgot to remove it before merging. 

Branch: model_refactor
https://code.cs.umanitoba.ca/winter-2022-a02/group-15/simple-forum/-/tree/model_refactor


Architecture File for Iteration 2
---------------------------------

Here's a link to this iteration's updated architecture file:

https://code.cs.umanitoba.ca/winter-2022-a02/group-15/simple-forum/-/blob/main/architecture_iteration2.md
