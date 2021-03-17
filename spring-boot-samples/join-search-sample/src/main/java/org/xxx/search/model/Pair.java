package org.xxx.search.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author Deveik
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Pair<K, V> {
    K key;
    V value;
}
