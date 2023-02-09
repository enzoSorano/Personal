//header file
#include "hcompress.h"

typedef struct node {
  tnode* value;
  struct node* next;
}LinkedList;

//prototypes
LinkedList* llCreate();
int llIsEmpty(LinkedList* ll);
void llDislay(LinkedList* ll);
void llAdd(LinkedList** ll, int newValue);
void llAddInOrder(LinkedList** ll, tnode* tNode);
void llFree(LinkedList* ll);
