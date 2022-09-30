package com.interview.others;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Streams {

	public static void main(String[] args) {
		List<String> myList = Arrays.asList("a1", "a2", "b1", "c2", "c1");

		myList.stream().filter(s -> s.startsWith("c")).map(String::toUpperCase).sorted().forEach(System.out::println);

		Arrays.asList("a1", "a2", "a3").stream().findFirst().ifPresent(System.out::println); // a1

		Stream.of("a1", "a2", "a3").findFirst().ifPresent(System.out::println); // a1

		IntStream.range(1, 4).forEach(System.out::println);

		Arrays.stream(new int[] { 1, 2, 3 }).map(n -> 2 * n + 1).average().ifPresent(System.out::println); // 5.0

		Stream.of("a1", "a2", "a3").map(s -> s.substring(1)).mapToInt(Integer::parseInt).max()
				.ifPresent(System.out::println); // 3

		IntStream.range(1, 4).mapToObj(i -> "a" + i).forEach(System.out::println);

		Stream.of(1.0, 2.0, 3.0).mapToInt(Double::intValue).mapToObj(i -> "a" + i).forEach(System.out::println);

		Stream.of("d2", "a2", "b1", "b3", "c").filter(s -> {
			System.out.println("filter: " + s);
			return true;
		});

		Stream.of("d2", "a2", "b1", "b3", "c").filter(s -> {
			System.out.println("filter: " + s);
			return true;
		}).forEach(s -> System.out.println("forEach: " + s));

		Stream.of("d2", "a2", "b1", "b3", "c").map(s -> {
			System.out.println("map: " + s);
			return s.toUpperCase();
		}).anyMatch(s -> {
			System.out.println("anyMatch: " + s);
			return s.startsWith("A");
		});

		filterOrder();

		Supplier<Stream<String>> streamSupplier = () -> Stream.of("d2", "a2", "b1", "b3", "c")
				.filter(s -> s.startsWith("a"));

		streamSupplier.get().anyMatch(s -> true); // ok
		streamSupplier.get().noneMatch(s -> true); // ok

		collectionsTest();
		
		fooBarTest();
		
		optionalTest();
		
//		Stream<Integer> infiniteStream = Stream.iterate(2, i -> i * 2);
//		infiniteStream.forEach(System.out::println);
		
	    List<Integer> intList = Arrays.asList(2, 4, 5, 6, 8);
	    
	    boolean allEven = intList.stream().allMatch(i -> i % 2 == 0);
	    boolean oneEven = intList.stream().anyMatch(i -> i % 2 == 0);
	    boolean noneMultipleOfThree = intList.stream().noneMatch(i -> i % 3 == 0);
	    
		

	}

	private static void optionalTest() {
//		Optional.of(new Foo(""))
//	    .flatMap(o -> Optional.ofNullable(o.nested))
//	    .flatMap(n -> Optional.ofNullable(n.inner))
//	    .flatMap(i -> Optional.ofNullable(i.foo))
//	    .ifPresent(System.out::println);
		
	}

	private static void fooBarTest() {
		List<Foo> foos = new ArrayList<>();

		// create foos
		IntStream
		    .range(1, 4)
		    .forEach(i -> foos.add(new Foo("Foo" + i)));

		// create bars
		foos.forEach(f ->
		    IntStream
		        .range(1, 4)
		        .forEach(i -> f.bars.add(new Bar("Bar" + i + " <- " + f.name))));
		
		foos.stream()
	    .flatMap(f -> f.bars.stream())
	    .forEach(b -> System.out.println(b.name));
		
		IntStream.range(1, 4)
	    .mapToObj(i -> new Foo("Foo" + i))
	    .peek(f -> IntStream.range(1, 4)
	        .mapToObj(i -> new Bar("Bar" + i + " <- " + f.name))
	        .forEach(f.bars::add))
	    .flatMap(f -> f.bars.stream())
	    .forEach(b -> System.out.println(b.name));
		
		
	}

	private static void collectionsTest() {
		List<Person> persons = Arrays.asList(new Person("Max", 18), new Person("Peter", 23), new Person("Pamela", 23),
				new Person("David", 12));

		List<Person> filtered = persons.stream().filter(p -> p.name.startsWith("P")).collect(Collectors.toList());

		System.out.println(filtered); // [Peter, Pamela]

		Map<Integer, List<Person>> personsByAge = persons.stream().collect(Collectors.groupingBy(p -> p.age));

		personsByAge.forEach((age, p) -> System.out.format("age %s: %s\n", age, p));

		Double averageAge = persons.stream().collect(Collectors.averagingInt(p -> p.age));

		System.out.println(averageAge); // 19.0

		String phrase = persons.stream().filter(p -> p.age >= 18).map(p -> p.name)
				.collect(Collectors.joining(" and ", "In Germany ", " are of legal age."));

		System.out.println(phrase);

		Map<Integer, String> map = persons.stream()
				.collect(Collectors.toMap(p -> p.age, p -> p.name, (name1, name2) -> name1 + ";" + name2));

		System.out.println(map);
		
		Collector<Person, StringJoiner, String> personNameCollector =
			    Collector.of(
			        () -> new StringJoiner(" | "),          // supplier
			        (j, p) -> j.add(p.name.toUpperCase()),  // accumulator
			        (j1, j2) -> j1.merge(j2),               // combiner
			        StringJoiner::toString);                // finisher

			String names = persons
			    .stream()
			    .collect(personNameCollector);

			System.out.println(names);  // MAX | PETER | PAMELA | DAVID
			
			Person result =
				    persons
				        .stream()
				        .reduce(new Person("", 0), (p1, p2) -> {
				            p1.age += p2.age;
				            p1.name += p2.name;
				            return p1;
				        });
			System.out.format("name=%s; age=%s", result.name, result.age);
			
			Integer ageSum = persons
				    .stream()
				    .reduce(0, (sum, p) -> sum += p.age, (sum1, sum2) -> sum1 + sum2);

				System.out.println(ageSum);  // 76
				
				
				ageSum = persons
					    .parallelStream()
					    .reduce(0,
					        (sum, p) -> {
					            System.out.format("accumulator: sum=%s; person=%s\n", sum, p);
					            return sum += p.age;
					        },
					        (sum1, sum2) -> {
					            System.out.format("combiner: sum1=%s; sum2=%s\n", sum1, sum2);
					            return sum1 + sum2;
					        });
				
				Arrays.asList("a1", "a2", "b1", "c2", "c1")
			    .parallelStream()
			    .filter(s -> {
			        System.out.format("filter: %s [%s]\n",
			            s, Thread.currentThread().getName());
			        return true;
			    })
			    .map(s -> {
			        System.out.format("map: %s [%s]\n",
			            s, Thread.currentThread().getName());
			        return s.toUpperCase();
			    })
			    .sorted((s1, s2) -> {
			        System.out.format("sort: %s <> %s [%s]\n",
			            s1, s2, Thread.currentThread().getName());
			        return s1.compareTo(s2);
			    })
			    .forEach(s -> System.out.format("forEach: %s [%s]\n",
			        s, Thread.currentThread().getName()));		
				
				
				persons
			    .parallelStream()
			    .reduce(0,
			        (sum, p) -> {
			            System.out.format("accumulator: sum=%s; person=%s [%s]\n",
			                sum, p, Thread.currentThread().getName());
			            return sum += p.age;
			        },
			        (sum1, sum2) -> {
			            System.out.format("combiner: sum1=%s; sum2=%s [%s]\n",
			                sum1, sum2, Thread.currentThread().getName());
			            return sum1 + sum2;
			        });				
	}

	private static void filterOrder() {
		Stream.of("d2", "a2", "b1", "b3", "c").map(s -> {
			System.out.println("map: " + s);
			return s.toUpperCase();
		}).filter(s -> {
			System.out.println("filter: " + s);
			return s.startsWith("A");
		}).forEach(s -> System.out.println("forEach: " + s));

		Stream.of("d2", "a2", "b1", "b3", "c").filter(s -> {
			System.out.println("filter: " + s);
			return s.startsWith("a");
		}).map(s -> {
			System.out.println("map: " + s);
			return s.toUpperCase();
		}).forEach(s -> System.out.println("forEach: " + s));

		// sort, then filter
		Stream.of("d2", "a2", "b1", "b3", "c").sorted((s1, s2) -> {
			System.out.printf("sort: %s; %s\n", s1, s2);
			return s1.compareTo(s2);
		}).filter(s -> {
			System.out.println("filter: " + s);
			return s.startsWith("a");
		}).map(s -> {
			System.out.println("map: " + s);
			return s.toUpperCase();
		}).forEach(s -> System.out.println("forEach: " + s));

	}
	

	static class Person {
		String name;
		int age;

		Person(String name, int age) {
			this.name = name;
			this.age = age;
		}

		@Override
		public String toString() {
			return name;
		}
	}
	
	static class Foo {
	    String name;
	    List<Bar> bars = new ArrayList<>();

	    Foo(String name) {
	        this.name = name;
	    }
	}

	static class Bar {
	    String name;

	    Bar(String name) {
	        this.name = name;
	    }
	}	
}
