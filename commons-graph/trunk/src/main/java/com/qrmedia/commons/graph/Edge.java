/*
 * @(#)Edge.java     6 Dec 2007
 *
 * Copyright © 2009 Andrew Phillips.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.qrmedia.commons.graph;

import com.qrmedia.commons.collections.Pair;


/**
 * An (undirected) edge in a graph.
 * 
 * @author anph
 * @since 6 Dec 2007
 *
 */
public class Edge extends Pair<Integer, Integer>{
    
    /** 
     * Creates an edge with the given end nodes.
     * 
     * @param firstNode the edge's first node
     * @param secondNode the edge's second node
     */
    public Edge(int firstNode, int secondNode) {
        super(firstNode, secondNode);
    }

    public int getFirstNode() {
        
        // assume a non-null object
        assert (firstObject != null) : this;
        return firstObject.intValue();
    }

    public int getSecondNode() {
        
        // assume a non-null object
        assert (secondObject != null) : this;        
        return secondObject.intValue();
    }

}
