public class FastScanner {
  BufferedReader br;
  StringTokenizer st;

  FastScanner(InputStream stream) {
    try {
      br = new BufferedReader(new
      InputStreamReader(stream));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  String next() {
    while (st == null || !st.hasMoreTokens()) {
    try {
      st = new StringTokenizer(br.readLine());
    } catch (IOException e) {
      e.printStackTrace();
    }
    }
    return st.nextToken();
  }

  int nextInt() {
    return Integer.parseInt(next());
  }
}