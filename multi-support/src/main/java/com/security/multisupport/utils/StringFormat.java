package com.security.multisupport.utils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @desc
 * @author: wu
 * @name: StringFormat
 * @date: 2021/11/4 下午3:16
 * @week: 周四
 */
public class StringFormat {


    /**
     * Prefix for recursion.
     */
    private static final String RECURSION_PREFIX = "[...";
    /**
     * Suffix for recursion.
     */
    private static final String RECURSION_SUFFIX = "...]";

    /**
     * Prefix for errors.
     */
    private static final String ERROR_PREFIX = "[!!!";
    /**
     * Separator for errors.
     */
    private static final String ERROR_SEPARATOR = "=>";
    /**
     * Separator for error messages.
     */
    private static final String ERROR_MSG_SEPARATOR = ":";
    /**
     * Suffix for errors.
     */
    private static final String ERROR_SUFFIX = "!!!]";

    private static final char DELIM_START = '{';
    private static final char DELIM_STOP = '}';
    private static final char ESCAPE_CHAR = '\\';

    private StringFormat() {
    }

    /**
     * Replace placeholders in the given messagePattern with arguments.
     *
     * @param messagePattern the message pattern containing placeholders.
     * @param arguments      the arguments to be used to replace placeholders.
     * @return the formatted message.
     */
    public static String format(final String messagePattern, final Object... arguments) {
        if (messagePattern == null || arguments == null || arguments.length == 0) {
            return messagePattern;
        }
        if (arguments instanceof String[]) {
            return formatStringArgs(messagePattern, (String[]) arguments);
        }
        final String[] stringArgs = new String[arguments.length];
        for (int i = 0; i < arguments.length; i++) {
            stringArgs[i] = String.valueOf(arguments[i]);
        }
        return formatStringArgs(messagePattern, stringArgs);
    }

    /**
     * Replace placeholders in the given messagePattern with arguments.
     * <p>
     * Package protected for unit tests.
     *
     * @param messagePattern the message pattern containing placeholders.
     * @param arguments      the arguments to be used to replace placeholders.
     * @return the formatted message.
     */
    static String formatStringArgs(final String messagePattern, final String[] arguments) {
        int len = 0;
        if (messagePattern == null || (len = messagePattern.length()) == 0 || arguments == null
                || arguments.length == 0) {
            return messagePattern;
        }

        return formatStringArgs0(messagePattern, len, arguments);
    }

    private static String formatStringArgs0(final String messagePattern, final int len, final String[] arguments) {
        final char[] result = new char[len + sumStringLengths(arguments)];
        int pos = 0;
        int escapeCounter = 0;
        int currentArgument = 0;
        int i = 0;
        for (; i < len - 1; i++) { // last char is excluded from the loop
            final char curChar = messagePattern.charAt(i);
            if (curChar == ESCAPE_CHAR) {
                escapeCounter++;
            } else {
                if (isDelimPair(curChar, messagePattern, i)) { // looks ahead one char
                    i++;

                    // write escaped escape chars
                    pos = writeEscapedEscapeChars(escapeCounter, result, pos);

                    if (isOdd(escapeCounter)) {
                        // i.e. escaped
                        // write escaped escape chars
                        pos = writeDelimPair(result, pos);
                    } else {
                        // unescaped
                        pos = writeArgOrDelimPair(arguments, currentArgument, result, pos);
                        currentArgument++;
                    }
                } else {
                    pos = handleLiteralChar(result, pos, escapeCounter, curChar);
                }
                escapeCounter = 0;
            }
        }
        pos = handleRemainingCharIfAny(messagePattern, len, result, pos, escapeCounter, i);
        return new String(result, 0, pos);
    }

    /**
     * Returns the sum of the lengths of all Strings in the specified array.
     */
    private static int sumStringLengths(final String[] arguments) {
        int result = 0;
        for (int i = 0; i < arguments.length; i++) {
            result += String.valueOf(arguments[i]).length();
        }
        return result;
    }

    /**
     * Returns {@code true} if the specified char and the char at {@code curCharIndex + 1} in the specified message
     * pattern together form a "{}" delimiter pair, returns {@code false} otherwise.
     */
    private static boolean isDelimPair(final char curChar, final String messagePattern, final int curCharIndex) {
        return curChar == DELIM_START && messagePattern.charAt(curCharIndex + 1) == DELIM_STOP;
    }

    /**
     * Detects whether the message pattern has been fully processed or if an unprocessed character remains and processes
     * it if necessary, returning the resulting position in the result char array.
     */
    private static int handleRemainingCharIfAny(final String messagePattern, final int len, final char[] result,
                                                int pos, int escapeCounter, int i) {
        if (i == len - 1) {
            final char curChar = messagePattern.charAt(i);
            pos = handleLastChar(result, pos, escapeCounter, curChar);
        }
        return pos;
    }

    /**
     * Processes the last unprocessed character and returns the resulting position in the result char array.
     */
    private static int handleLastChar(final char[] result, int pos, final int escapeCounter, final char curChar) {
        if (curChar == ESCAPE_CHAR) {
            pos = writeUnescapedEscapeChars(escapeCounter + 1, result, pos);
        } else {
            pos = handleLiteralChar(result, pos, escapeCounter, curChar);
        }
        return pos;
    }

    /**
     * Processes a literal char (neither an '\' escape char nor a "{}" delimiter pair) and returns the resulting
     * position.
     */
    private static int handleLiteralChar(final char[] result, int pos, final int escapeCounter, final char curChar) {
        // any other char beside ESCAPE or DELIM_START/STOP-combo
        // write unescaped escape chars
        pos = writeUnescapedEscapeChars(escapeCounter, result, pos);
        result[pos++] = curChar;
        return pos;
    }

    /**
     * Writes "{}" to the specified result array at the specified position and returns the resulting position.
     */
    private static int writeDelimPair(final char[] result, int pos) {
        result[pos++] = DELIM_START;
        result[pos++] = DELIM_STOP;
        return pos;
    }

    /**
     * Returns {@code true} if the specified parameter is odd.
     */
    private static boolean isOdd(final int number) {
        return (number & 1) == 1;
    }

    /**
     * Writes a '\' char to the specified result array (starting at the specified position) for each <em>pair</em> of
     * '\' escape chars encountered in the message format and returns the resulting position.
     */
    private static int writeEscapedEscapeChars(final int escapeCounter, final char[] result, final int pos) {
        final int escapedEscapes = escapeCounter >> 1; // divide by two
        return writeUnescapedEscapeChars(escapedEscapes, result, pos);
    }

    /**
     * Writes the specified number of '\' chars to the specified result array (starting at the specified position) and
     * returns the resulting position.
     */
    private static int writeUnescapedEscapeChars(int escapeCounter, char[] result, int pos) {
        while (escapeCounter > 0) {
            result[pos++] = ESCAPE_CHAR;
            escapeCounter--;
        }
        return pos;
    }

    /**
     * Appends the argument at the specified argument index (or, if no such argument exists, the "{}" delimiter pair) to
     * the specified result char array at the specified position and returns the resulting position.
     */
    private static int writeArgOrDelimPair(final String[] arguments, final int currentArgument, final char[] result,
                                           int pos) {
        if (currentArgument < arguments.length) {
            pos = writeArgAt0(arguments, currentArgument, result, pos);
        } else {
            pos = writeDelimPair(result, pos);
        }
        return pos;
    }

    /**
     * Appends the argument at the specified argument index to the specified result char array at the specified position
     * and returns the resulting position.
     */
    private static int writeArgAt0(final String[] arguments, final int currentArgument, final char[] result,
                                   final int pos) {
        final String arg = String.valueOf(arguments[currentArgument]);
        int argLen = arg.length();
        arg.getChars(0, argLen, result, pos);
        return pos + argLen;
    }


    /**
     * This method performs a deep toString of the given Object.
     * Primitive arrays are converted using their respective Arrays.toString methods while
     * special handling is implemented for "container types", i.e. Object[], Map and Collection because those could
     * contain themselves.
     * <p>
     * dejaVu is used in case of those container types to prevent an endless recursion.
     * </p>
     * <p>
     * It should be noted that neither AbstractMap.toString() nor AbstractCollection.toString() implement such a
     * behavior.
     * They only check if the container is directly contained in itself, but not if a contained container contains the
     * original one. Because of that, Arrays.toString(Object[]) isn't safe either.
     * Confusing? Just read the last paragraph again and check the respective toString() implementation.
     * </p>
     * <p>
     * This means, in effect, that logging would produce a usable output even if an ordinary System.out.println(o)
     * would produce a relatively hard-to-debug StackOverflowError.
     * </p>
     *
     * @param o      the Object to convert into a String
     * @param str    the StringBuilder that o will be appended to
     * @param dejaVu a list of container identities that were already used.
     */
    private static void recursiveDeepToString(final Object o, final StringBuilder str, final Set<String> dejaVu) {
        if (appendStringDateOrNull(o, str)) {
            return;
        }
        if (isMaybeRecursive(o)) {
            appendPotentiallyRecursiveValue(o, str, dejaVu);
        } else {
            tryObjectToString(o, str);
        }
    }

    private static boolean appendStringDateOrNull(final Object o, final StringBuilder str) {
        if (o == null || o instanceof String) {
            str.append(String.valueOf(o));
            return true;
        }
        return appendDate(o, str);
    }

    private static boolean appendDate(final Object o, final StringBuilder str) {
        if (!(o instanceof Date)) {
            return false;
        }
        final Date date = (Date) o;
        final SimpleDateFormat format = getSimpleDateFormat();
        str.append(format.format(date));
        return true;
    }

    private static SimpleDateFormat getSimpleDateFormat() {
        // I'll leave it like this for the moment... this could probably be optimized using ThreadLocal...
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
    }

    /**
     * Returns {@code true} if the specified object is an array, a Map or a Collection.
     */
    private static boolean isMaybeRecursive(final Object o) {
        return o.getClass().isArray() || o instanceof Map || o instanceof Collection;
    }

    private static void appendPotentiallyRecursiveValue(final Object o, final StringBuilder str,
                                                        final Set<String> dejaVu) {
        final Class<?> oClass = o.getClass();
        if (oClass.isArray()) {
            appendArray(o, str, dejaVu, oClass);
        } else if (o instanceof Map) {
            appendMap(o, str, dejaVu);
        } else if (o instanceof Collection) {
            appendCollection(o, str, dejaVu);
        }
    }

    private static void appendArray(final Object o, final StringBuilder str, final Set<String> dejaVu,
                                    final Class<?> oClass) {
        if (oClass == byte[].class) {
            str.append(Arrays.toString((byte[]) o));
        } else if (oClass == short[].class) {
            str.append(Arrays.toString((short[]) o));
        } else if (oClass == int[].class) {
            str.append(Arrays.toString((int[]) o));
        } else if (oClass == long[].class) {
            str.append(Arrays.toString((long[]) o));
        } else if (oClass == float[].class) {
            str.append(Arrays.toString((float[]) o));
        } else if (oClass == double[].class) {
            str.append(Arrays.toString((double[]) o));
        } else if (oClass == boolean[].class) {
            str.append(Arrays.toString((boolean[]) o));
        } else if (oClass == char[].class) {
            str.append(Arrays.toString((char[]) o));
        } else {
            // special handling of container Object[]
            final String id = identityToString(o);
            if (dejaVu.contains(id)) {
                str.append(RECURSION_PREFIX).append(id).append(RECURSION_SUFFIX);
            } else {
                dejaVu.add(id);
                final Object[] oArray = (Object[]) o;
                str.append('[');
                boolean first = true;
                for (final Object current : oArray) {
                    if (first) {
                        first = false;
                    } else {
                        str.append(", ");
                    }
                    recursiveDeepToString(current, str, new HashSet<>(dejaVu));
                }
                str.append(']');
            }
            //str.append(Arrays.deepToString((Object[]) o));
        }
    }

    private static void appendMap(final Object o, final StringBuilder str, final Set<String> dejaVu) {
        // special handling of container Map
        final String id = identityToString(o);
        if (dejaVu.contains(id)) {
            str.append(RECURSION_PREFIX).append(id).append(RECURSION_SUFFIX);
        } else {
            dejaVu.add(id);
            final Map<?, ?> oMap = (Map<?, ?>) o;
            str.append('{');
            boolean isFirst = true;
            for (final Object o1 : oMap.entrySet()) {
                final Map.Entry<?, ?> current = (Map.Entry<?, ?>) o1;
                if (isFirst) {
                    isFirst = false;
                } else {
                    str.append(", ");
                }
                final Object key = current.getKey();
                final Object value = current.getValue();
                recursiveDeepToString(key, str, new HashSet<>(dejaVu));
                str.append('=');
                recursiveDeepToString(value, str, new HashSet<>(dejaVu));
            }
            str.append('}');
        }
    }

    private static void appendCollection(final Object o, final StringBuilder str, final Set<String> dejaVu) {
        // special handling of container Collection
        final String id = identityToString(o);
        if (dejaVu.contains(id)) {
            str.append(RECURSION_PREFIX).append(id).append(RECURSION_SUFFIX);
        } else {
            dejaVu.add(id);
            final Collection<?> oCol = (Collection<?>) o;
            str.append('[');
            boolean isFirst = true;
            for (final Object anOCol : oCol) {
                if (isFirst) {
                    isFirst = false;
                } else {
                    str.append(", ");
                }
                recursiveDeepToString(anOCol, str, new HashSet<>(dejaVu));
            }
            str.append(']');
        }
    }

    private static void tryObjectToString(final Object o, final StringBuilder str) {
        // it's just some other Object, we can only use toString().
        try {
            str.append(o.toString());
        } catch (final Throwable t) {
            handleErrorInObjectToString(o, str, t);
        }
    }

    private static void handleErrorInObjectToString(final Object o, final StringBuilder str, final Throwable t) {
        str.append(ERROR_PREFIX);
        str.append(identityToString(o));
        str.append(ERROR_SEPARATOR);
        final String msg = t.getMessage();
        final String className = t.getClass().getName();
        str.append(className);
        if (!className.equals(msg)) {
            str.append(ERROR_MSG_SEPARATOR);
            str.append(msg);
        }
        str.append(ERROR_SUFFIX);
    }

    /**
     * This method returns the same as if Object.toString() would not have been
     * overridden in obj.
     * <p>
     * Note that this isn't 100% secure as collisions can always happen with hash codes.
     * </p>
     * <p>
     * Copied from Object.hashCode():
     * </p>
     * <blockquote>
     * As much as is reasonably practical, the hashCode method defined by
     * class {@code Object} does return distinct integers for distinct
     * objects. (This is typically implemented by converting the internal
     * address of the object into an integer, but this implementation
     * technique is not required by the Java&#8482; programming language.)
     * </blockquote>
     *
     * @param obj the Object that is to be converted into an identity string.
     * @return the identity string as also defined in Object.toString()
     */
    private static String identityToString(final Object obj) {
        if (obj == null) {
            return null;
        }
        return obj.getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(obj));
    }


}
