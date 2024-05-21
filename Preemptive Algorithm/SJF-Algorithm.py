def sum(l,i) :
    s=0
    for j in range(i):
        s+=l[j]
    s=s/i
    return s

id = int(input("Enter total number process :"))
at=[]
bt=[] 

for i in range(id):
    a= int(input("Enter Arrival time of id "))
    b = int(input("Enter Burst time of id "))
    at.append(a)    
    bt.append(b)

atc=at.copy()
atc1=at.copy()
maxi = 0
if max(at)>max(bt) :
    maxi =max(at)+1
else:
    maxi = max(bt)+1
gt = [0]
ind = []
at.sort()
at.append(0)
atc1.append(0)
bt.append(maxi)
j=0
for i in range(id):
    m=atc1.index(at[i])
    atc1[m] = maxi
    nm = atc1.index(at[i+1])
    if gt[j]<atc[m] :
        t = gt[j]+( atc[m]-gt[i])
        gt.append(t)
        j+=1
    if bt[m]<=bt[nm]:
        gt.append(gt[j]+bt[m])
        ind.append(m)
    else :
        gt.append(gt[j]+bt[nm])
        ind.append(nm)
        temp = atc1[m]
        atc1[m] = atc1[nm]
        atc1[nm] = temp
    print(ind)
    print(gt)
    j+=1

del maxi
del at
del atc1

tat = []
wt = []
ct = []
j=0
for i in range(id):
    if gt[j]<atc[ind[i]] :
        j+=1
    ct.append(gt[j+1])
    tat.append(ct[i]-atc[ind[i]])
    wt.append(tat[i]-bt[ind[i]])
    j+=1

avg_tat = sum(tat,id)
avg_wt = sum(wt,id)

print("Output: ")
print("id\tat\tbt\tct\ttat\twt")
for i in range(id):
    a=atc[i]
    b=bt[i]
    j=ind.index(i)
    c=ct[j]
    t=tat[j]
    w=wt[j]
    print(i,"\t",a,"\t", b, "\t",c,"\t",t,"\t",w) 
print("Average Turn Around Time : ",avg_tat)
print("Average Waiting Time :",avg_wt)