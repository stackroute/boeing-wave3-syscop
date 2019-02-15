package io.promagent.hooks;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


/**Storing the http context in a thread to know which database request call was triggered.
*/
class HttpContext {

    static class Key<T> {}

    static final Key<String> HTTP_METHOD = new Key<>();
    static final Key<String> HTTP_PATH = new Key<>();

    private static final ThreadLocal<Map<Key, Object>> threadLocal = ThreadLocal.withInitial(HashMap::new);

    static <T> void put(Key<T> key, T value) {
        threadLocal.get().put(key, value);
    }

    @SuppressWarnings("unchecked")
    static <T> Optional<T> get(Key<T> key) {
        return Optional.ofNullable((T) threadLocal.get().get(key));
    }

    static void clear(Key... keys) {
        for (Key key : keys) {
            threadLocal.get().remove(key);
        }
    }
}
