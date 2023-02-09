#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
#include "linkedList.h" //this is our header file that is linked to the hcompress header fill


//We call our functions, then create new files after they are compressed or uncompressed
int main(int agrc, char* argv[]) {
  //check o make sure the input parameters are correct

  if(agrc !=3) {
    printf("Error: the correct format is \"hcompress -e filename\" or \"hcompress -d filename.huf\"\n");

    fflush(stdout);

   exit(1);
  }
  //create the frequency table by reading the generic file
  struct tnode* leaves = createFreqTable("declaration.txt");

  //create the huffman tree from the frequency table
   struct tnode* treeRoot = createHuffmanTree(leaves);

  //encode
  if(strcmp(argv[1], "-e") == 0) {
    //pass the leafNodes since it will process bottom up
    encodeFile(argv[2], leaves);
  }
  else { //decode
    //pass the tree root since it will process top down
    decodeFile(argv[2], treeRoot);
  }

   freeTree(treeRoot);
   free(leaves);
  return 0;
}
//creates a table of characters from least to most frequent
tnode* createFreqTable(char* fileName){
  tnode* leaves = (tnode*) malloc(127 * sizeof(tnode));
  FILE* file = fopen(fileName, "r");

  if(file == NULL){
    printf("Invalid file\n");
    exit(1);
  }
  char tmp = 0;
  while(fscanf(file, "%c", &tmp) != EOF) {
    leaves[(int) tmp].weight += 1;
    leaves[(int) tmp].c = tmp;
  }
  fclose(file);
  return leaves;
}
//creates our tree using the huffman greedy algorithm that takes the lowest occuring 2 nodes
//and makes a new node with the old nodes as children
tnode* createHuffmanTree(tnode* leaves) {
  tnode* root;
  LinkedList* p;
  LinkedList* list = llCreate();

  for(int i = 0; i < 127; i++){
    //printf("create huffman for loop\n");
    if(leaves[i].weight != 0)
      llAddInOrder(&list, &leaves[i]);
  }
  p = list;

  while(p->next != NULL) {
    tnode* newNode = (tnode*) malloc(1 * sizeof(tnode));
    newNode->c = 0;
    p->value->parent = newNode;
    newNode->left = p->value;
    p->next->value->parent = newNode;
    newNode->right = p->next->value;
    newNode->weight = newNode->right->weight + newNode ->left->weight;

    llAddInOrder(&list, newNode);
    root = newNode;
    if(p->next->next != NULL)
      p = p->next->next;
  }
  p = list;

  while(p->next != NULL) {
    LinkedList* tmp = p;
    p = p->next;
     free(tmp);
   }

free(p);
return root;
}

//We encode the file by assigning each character a binary value and then compressing the file
void encodeFile(char* fileName, tnode* leaves){
FILE* file = fopen(fileName, "r");
FILE* writeFile = fopen(strcat(fileName, ".huf"), "w");

if(file == NULL){
 printf("Invalid file\n");
 exit(1);
}

char tmp = 0;
unsigned int huffCode[127];

memset(huffCode, 0, sizeof(huffCode));
int c = 0;
int height = 0;
unsigned char byte = 0;
while(fscanf(file, "%c", &tmp) != EOF){
 tnode* t = &leaves[(int) tmp];

 while(t->parent !=NULL){
   if(t == t->parent->right)
     huffCode[height] = 1;
       else
       huffCode[height] = 0;
     height++;
     if(t->parent != NULL)
       t = t->parent;
   }

   for(int i = height - 1; i >= 0; i--){
     if(c == 8) {
       fprintf(writeFile, "%c", byte);
       byte = 0;
       c = 0;
     }

     byte = byte | (huffCode[i] << (7 - c));
     c++;
     }
   height = 0;
 }
 fclose(file);
 fclose(writeFile);
}

//We reverse what we did to decompress the file
void decodeFile(char* fileName, tnode* treeRoot){
 FILE* file = fopen(fileName, "r");
 FILE* writeFile = fopen(strcat(fileName, ".dec"), "w");
 if(file == NULL) {
   printf("Invalid File\n");
   exit(1);
 }
 //We reverse what we did to decompress the file
void decodeFile(char* fileName, tnode* treeRoot){
  FILE* file = fopen(fileName, "r");
  FILE* writeFile = fopen(strcat(fileName, ".dec"), "w");
  if(file == NULL) {
    printf("Invalid File\n");
    exit(1);
  }
  unsigned char tmp = 0;
  tnode* t = treeRoot;
  //printf("before while\n");
  while(fscanf(file, "%c", &tmp) != EOF) {
    unsigned char byte = tmp;
    //printf("before forloop\n");
    for(int i = 7; i >= 0; i--) {
      if(((byte & (1 << i)) >> i) == 1 && t->right !=NULL){
        t = t->right;
        //printf("this one\n");
      } else if (((byte & (1 << i)) >> i) == 0 && t->left != NULL){
          t = t->left;
          //printf("secondtest\n");
        } else if(t->right == NULL && t->left == NULL) {
        //      printf("before fprint\n");
        fprintf(writeFile, "%c", t->c);
          t = treeRoot;
          //  printf("treeroot issue\n");
          i++;
        }
      // printf("after\n");
    }
  }
}
}
//Free up all the memory we left behind
  void freeTree(tnode* node){
    if(node == NULL)
      return;

    if(node->right != NULL)
      freeTree(node->right);
    else return;
    if(node ->left != NULL)
      freeTree(node->left);
    else return;

    free(node);
  }
