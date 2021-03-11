
mvn test -Dkarate.env=dev


For running tests using tags with multiple conditions, we need to use logic conditions in tags.

AND:

To run every feature that has both @F1 and @F2 tags.

{"@F1", "@F2"}

OR:

To run every feature that has either of the @F1 and @F2 tags (runs both)

{"@F1,@F2"}

Combining OR and AND:

To run feature that has either of @F1,@F2,@F3 tags but not @F4 tag

{"@F1,@F2,@F3","~@F4"}

dependency:purge-local-repository