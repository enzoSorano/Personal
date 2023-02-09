#include <iostream>
#include <omp.h>
// linux1/2/3/4: openMP is alread installed.  Just need -fopenmp flag:
//    g++ piomp.cc -fopenmp -o pi
// mac: Need to install openMP with 'brew install libomp'
//    For Intel chips you need preprocessor and omp lib flags:
//      g++ piomp.cc -Xpreprocessor -fopenmp -lomp -o pi
//
//    For M1 (arm) chips you also need to give different include/lib paths
//      g++ piomp.cc -Xpreprocessor -fopenmp -lomp -L/opt/homebrew/opt/libomp/li
#include <mutex>
#include <string>


std::mutex screenLock;
int numPrimesA = 0;
int numPrimesB = 0;

int isPrime(int n);

void blockingParallel(int startIndex, int endIndex);

void stripingParallel(int startIndex, int endIndex);

int main(int argc, char* argv[]) {
  if (argc != 3) {
    std::cout << "Expected 2 args!" << '\n';
    exit(1);
  }
  int startIndex = std::atoi(argv[1]), endIndex = std::atoi(argv[2]), numPrimes = 0;

  omp_set_num_threads(6);

  std::cout << "Blocking" << '\n';
  double t1 = omp_get_wtime();
  blockingParallel(startIndex, endIndex);
  double t2 = omp_get_wtime();
  std::cout << "Overall time" << ": " << (t2-t1) << " with " << numPrimesA << " found" << "\n";
  std::cout << "Striping" << '\n';
  double t3 = omp_get_wtime();
  stripingParallel(startIndex, endIndex);
  double t4 = omp_get_wtime();
  std::cout << "Overall time" << ": " << (t4-t3) << " with " << numPrimesA << " found" << "\n";

  return 0;
}

int isPrime(int n) {
  int i = 2;
  if (n <= 1)
      return 0;
  while (i < (n-1) && n % i != 0)
      i++;
  return n % i;
}

void blockingParallel(int startIndex, int endIndex) {
#pragma omp parallel reduction (+:numPrimesA)
{
  double t1 = omp_get_wtime();
  int block = ((endIndex-startIndex))/omp_get_num_threads();
#pragma omp for schedule(static, block) nowait
  for (int i = startIndex; i < endIndex; i++) {
    if (isPrime(i)) {
      numPrimesA++;
    }
}
  double t2 = omp_get_wtime();
  screenLock.lock();
  std::cout << "\tTime for thread " << omp_get_thread_num() << ": " << (t2-t1) << " with " << numPrimesA << " found" << "\n";
  screenLock.unlock();
}
}

void stripingParallel(int startIndex, int endIndex) {
#pragma omp parallel reduction (+:numPrimesB)
{
  double t1 = omp_get_wtime();
#pragma omp for schedule(static, omp_get_num_threads()) nowait
  for (int i = startIndex; i < endIndex; i++) {
    if (isPrime(i))
      numPrimesB++;
  }
  double t2 = omp_get_wtime();
  screenLock.lock();
  std::cout << "\tTime for thread " << omp_get_thread_num() << ": " << (t2-t1) << " with " << numPrimesB << " found" << "\n";
  screenLock.unlock();
}
}
