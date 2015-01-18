import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

/**
 * Contains the general algorithm to search for doublets.
 * 
 * @author <<YOUR NAME(S) HERE>> Created Mar 18, 2011.
 */
public class Doublets {
	
	private static String dictionaryName = "english.cleaned.all.35.txt";

	public static void main(String[] args) {
		System.out
				.println("*************************************************************************************");
		System.out.print("** Loading the dictionary from '" + dictionaryName
				+ "', please wait ...");
		Links link = new Links(dictionaryName);
		System.out.println("Done!! **");
		System.out
				.println("**********************************  Game Start!!!  **********************************");
		System.out
				.println("*************************************************************************************");

		while (true) {
			System.out.print("Enter starting word: ");
			Scanner in = new Scanner(System.in);
			String startWord = in.next();
			String endWord = "";
			String chainManager = "";
			if (isWord(startWord, link)) {
				System.out.print("Enter ending word: ");
				endWord = in.next();

				if (isWord(endWord, link)) {
					if (startWord.length() == endWord.length()) {
						System.out
								.print("Enter chain manager (s: stack, q: queue, p: priority queue, x: exit): ");
						chainManager = in.next();
					} else {
						System.out.print("There is no way to transfor '"
								+ startWord + "' to '" + endWord
								+ "'. (r: try again, x: exit): ");
						chainManager = in.next();
					}
				} else {
					System.out.print("The ending word '" + endWord
							+ "' is not vaild. (r: try again, x: exit): ");
					chainManager = in.next();
				}
			} else {
				System.out.print("The starting word '" + startWord
						+ "' is not vaild. (r: try again, x: exit): ");
				chainManager = in.next();
			}

			if (chainManager.equals("s")) {
				ManagerWithStack(startWord, endWord, link);
			} else if (chainManager.equals("q")) {
				ManagerWithQueue(startWord, endWord, link);
			} else if (chainManager.equals("p")) {
				ManagerWithPQ(startWord, endWord, link);
			} else if (chainManager.equals("x")) {
				System.out.println("Goodbye!");
				break;
			}
		}
	}

	private static void ManagerWithPQ(String startWord, String endWord,
			Links link) {
		boolean print = true;
		PriorityQueueChainManager chains = new PriorityQueueChainManager(endWord);
		Chain c = new Chain();
		Set<String> s;
		c = c.addLast(startWord);
		chains.add(c);
		Chain currentChain = chains.next();
		String currentWord = currentChain.getLast();
		String str;
		while (!currentWord.equals(endWord)) {
			s = link.getCandidates(currentWord);
			if (!s.isEmpty()) {
				Iterator<String> iter = s.iterator();
				while (iter.hasNext()) {
					str = iter.next();
					Chain tempChain = new Chain();
					if (!currentChain.contains(str)) {
						tempChain = currentChain.addLast(str);
						chains.add(tempChain);
					}
				}
			}

			if (chains.pQueue.isEmpty()) {
				System.out.println("No doublet chain exists from '" + startWord
						+ "' to '" + endWord + "'.");
				print = false;
				break;
			} else {
				currentChain = chains.next();
				currentWord = (String) currentChain.getLast();
			}
		}
		if (print) {
			System.out.println("Chain: " + currentChain.s.toString());
			System.out.println("Length: " + currentChain.length());
			System.out.println("Candidates: " + chains.getNumberOfNexts());
			System.out.println("Max size: " + chains.maxSize());
		}
	}

	private static boolean isWord(String startWord, Links link) {
		return link.contains(startWord);
	}

	private static void ManagerWithQueue(String startWord, String endWord,
			Links link) {
		boolean print = true;
		QueueChainManager chains = new QueueChainManager();
		Chain c = new Chain();
		Set<String> s;
		c = c.addLast(startWord);
		chains.add(c);
		Chain currentChain = chains.next();
		String currentWord = (String) currentChain.getLast();
		String str;
		while (!currentWord.equals(endWord)) {
			s = link.getCandidates(currentWord);
			if (!s.isEmpty()) {
				Iterator<String> iter = s.iterator();
				while (iter.hasNext()) {
					str = iter.next();
					Chain tempChain = new Chain();
					if (!currentChain.contains(str)) {
						tempChain = currentChain.addLast(str);
						chains.add(tempChain);
					}
				}
			}

			if (chains.queue.isEmpty()) {
				System.out.println("No doublet chain exists from '" + startWord
						+ "' to '" + endWord + "'.");
				print = false;
				break;
			} else {
				currentChain = chains.next();
				currentWord = (String) currentChain.getLast();
			}
		}
		if (print) {
			System.out.println("Chain: " + currentChain.s.toString());
			System.out.println("Length: " + currentChain.length());
			System.out.println("Candidates: " + chains.getNumberOfNexts());
			System.out.println("Max size: " + chains.maxSize());
		}
	}

	private static void ManagerWithStack(String startWord, String endWord,
			Links link) {
		boolean print = true;
		StackChainManager chains = new StackChainManager();
		Chain c = new Chain();
		Set<String> s;
		c = c.addLast(startWord);
		chains.add(c);
		Chain currentChain = chains.next();
		String currentWord = (String) currentChain.getLast();
		String str;
		while (!currentWord.equals(endWord)) {
			StackChainManager temp = new StackChainManager();
			s = link.getCandidates(currentWord);
			if (!s.isEmpty()) {
				Iterator<String> iter = s.iterator();
				while (iter.hasNext()) {
					str = iter.next();
					Chain tempChain = new Chain();
					if (!currentChain.contains(str)) {
						tempChain = currentChain.addLast(str);
						temp.add(tempChain);
					}
				}
				while (!temp.chainStack.isEmpty()) {
					Chain tempChain = new Chain();
					tempChain = temp.next();
					chains.add(tempChain);
				}
			}

			if (chains.chainStack.isEmpty()) {
				System.out.println("No doublet chain exists from '" + startWord
						+ "' to '" + endWord + "'.");
				print = false;
				break;
			} else {
				currentChain = chains.next();
				currentWord = (String) currentChain.getLast();
			}
		}
		if (print) {
			System.out.println("Chain: " + currentChain.s.toString());
			System.out.println("Length: " + currentChain.length());
			System.out.println("Candidates: " + chains.getNumberOfNexts());
			System.out.println("Max size: " + chains.maxSize());
		}
	}
}
