
package io.agent.internal;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Metadata for a Hook class as parsed from the @Hook, @Before, and @After annotations.
 */
public class HookMetadata implements Comparable<HookMetadata> {

    private final String hookClassName;
    private final SortedSet<String> instruments;
    private final SortedSet<MethodSignature> methods;

    public static class MethodSignature implements Comparable<MethodSignature> {

        private final String methodName;
        private final List<String> parameterTypes;

        public MethodSignature(String methodName, List<String> parameterTypes) {
            this.methodName = methodName;
            this.parameterTypes = Collections.unmodifiableList(new ArrayList<>(parameterTypes));
        }

        public String getMethodName() {
            return methodName;
        }

        public List<String> getParameterTypes() {
            return parameterTypes;
        }

        @Override
        public String toString() {
            return methodName + "(" + String.join(", ", parameterTypes) + ")";
        }

        @Override
        public boolean equals(Object o) {
            return o != null && getClass() == o.getClass() && compareTo((MethodSignature) o) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(toString());
        }

        @Override
        public int compareTo(MethodSignature o) {
            return Comparator
                    .comparing(MethodSignature::getMethodName)
                    .thenComparing(MethodSignature::getParameterTypes, new LexicographicalComparator<>())
                    .compare(this, o);
        }
    }

    public HookMetadata(String hookClassName, Collection<String> instruments, Collection<MethodSignature> methods) {
        this.hookClassName = hookClassName;
        this.instruments = Collections.unmodifiableSortedSet(new TreeSet<>(instruments));
        this.methods = Collections.unmodifiableSortedSet(new TreeSet<>(methods));
    }

    public String getHookClassName() {
        return hookClassName;
    }

    public SortedSet<String> getInstruments() {
        return instruments;
    }

    public SortedSet<MethodSignature> getMethods() {
        return methods;
    }

    @Override
    public String toString() { // TODO: instruments is a Set
        String delimiter = System.lineSeparator() + "  * ";
        return hookClassName + " instruments [" + String.join(", ", instruments) + "]:" + delimiter + String.join(delimiter, strings(methods));
    }

    @Override
    public boolean equals(Object o) {
        return o != null && getClass() == o.getClass() && compareTo((HookMetadata) o) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(toString());
    }

    @Override
    public int compareTo(HookMetadata o) {
        return Comparator
                .comparing(HookMetadata::getHookClassName)
                .thenComparing(HookMetadata::getInstruments, new LexicographicalComparator<>())
                .thenComparing(HookMetadata::getMethods, new LexicographicalComparator<>())
                .compare(this, o);
    }

    private List<String> strings(Collection<?> list) {
        return list.stream().map(Object::toString).collect(Collectors.toList());
    }

    /**
     * Compare SortedSet instances or List instances in lexicographical order.
     */
    static class LexicographicalComparator<C extends Comparable<C>, T extends Iterable<C>> implements Comparator<T> {
        @Override
        public int compare(T list1, T list2) {
            Iterator<C> iterator1 = list1.iterator();
            Iterator<C> iterator2 = list2.iterator();
            while (iterator1.hasNext() && iterator2.hasNext()) {
                int result = iterator1.next().compareTo(iterator2.next());
                if (result != 0) {
                    return result;
                }
            }
            if (iterator1.hasNext()) {
                return 1;
            }
            if (iterator2.hasNext()) {
                return -1;
            }
            return 0;
        }
    }
}
