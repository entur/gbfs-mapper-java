# gbfs-mapper-java

Bidirectional mapper between versions 2.x and 3.x of GBFS.

Usage:

    var targetFeed = GBFSMapper.INSTANCE.map(sourceFeed, "en");

### Notes on language
Due to how internationalization works prior to version 3, this mapper only
supports mapping a single language. The language code must be given as the second argument
of the map method.

In case of mapping *to* version 3.x, the language argument will be used to populate all
language code fields. Conversely, when mapping *from* version 3.x, it will be used to lookup
the correct translation. Additional translations in the 3.x files will be lost.

For consistency, the language argument is required even for files that do not have any 
translations.

### Semantics of geofencing zones

In `geofencing_zones.json`, the semantics of the geometry was changed in version 3.x.
It used to be the case that "A clockwise arrangement of points defines the area enclosed 
by the polygon, while a counterclockwise order defines the area outside the polygon".

However, polygons are mapped as is, without regards to changes in semantics.
