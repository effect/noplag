package org.amse.fedotov.cs_algo.comparator;

import java.util.ArrayList;
import java.util.List;

import org.amse.fedotov.noplag.comparator.two.IProgramComparator;
import org.amse.fedotov.noplag.model.IProgram;
import org.amse.fedotov.noplag.model.IToken;

public class ComparatorBasedOnCommonSubstrings implements IProgramComparator<CommonSubstringsResult> {

	public CommonSubstringsResult compare(IProgram prog1, IProgram prog2) {
		List<IToken> a = prog1.getTokens();
		List<IToken> b = prog2.getTokens();
		int lengthA = a.size();
		int lengthB = b.size();
		int leftIndexB = lengthA;
		int rightIndexB = lengthA + lengthB - 1;
		List<List<CommonSubstringPart>> commonSubstringsLists = new ArrayList<List<CommonSubstringPart>>();
		for (int leftIndexA = 0, rightIndexA = lengthA - 1; leftIndexA < lengthA + lengthB; leftIndexA++, rightIndexA++) {
			int l = Math.max(leftIndexA, leftIndexB);
			int r = Math.min(rightIndexA, rightIndexB);
			List<CommonSubstringPart> parts = new ArrayList<CommonSubstringPart>();
			
			boolean isEqualSerie = false;
			int from = -1;
			int to = -1;
			for (int i = l; i <= r; i++) {
				if (a.get(i - leftIndexA) == b.get(i - leftIndexB)) {
					if (isEqualSerie) {
						to = i;
					} else {
						from = i;
					}
					isEqualSerie = true;
				} else {
					if (isEqualSerie) {
						to = i - 1;
						parts.add(new CommonSubstringPart(from - leftIndexA, to - leftIndexA, 
								from - leftIndexB, to - leftIndexB));
					} 
					isEqualSerie = false;
				}
			}
			if (isEqualSerie) {
				to = r;
				parts.add(new CommonSubstringPart(from - leftIndexA, to - leftIndexA, 
						from - leftIndexB, to - leftIndexB));
			}
			commonSubstringsLists.add(parts);
		}
		return new CommonSubstringsResult(commonSubstringsLists);
	}

}
