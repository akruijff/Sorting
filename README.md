# Sorting

Sorting is java library with different sorting algorithms.

## Installation

The java library is currently not in the maven central database.

## Usage

```java
import org.kruijff.sorting.*

Type[] arr = ...
Comparator<Type> comparator = ...
SortAlgorithm<Type> algorithm = new QuickSort<>(comparator);
algoritm.sort(arr);
```

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

