import threading
from time import strftime

def log(id, *args):
    timestr = strftime("%H:%M:%S")
    print('[{}] Thread {}: '.format(timestr, id), "".join(map(str, args)))
    
def hamming(BuildingX, BuildingY, AntennaX, AntennaY):
    return abs(BuildingX - AntennaX) + abs(BuildingY - AntennaY)

def notInRange(b, placedAntennas):
    if placedAntennas == []:
        return True
    for antenna in placedAntennas:
        if hamming(b[0], b[1], antenna[0], antenna[1]) <= antenna[2]:
            return False
    return True

def solve(file, id):
    file = "inputs/" + file

    with open(file, "r", encoding="utf-8") as f, \
        open(file[:-3]+"-output.txt", "w", encoding="utf-8") as fw:

        log(id, "Reading input")

        width, height = map(int, f.readline().split(" "))
        nBuildings, nAntennas, reward = map(int, f.readline().split(" "))

        # building = [x, y, latency, connection]
        buildings = [list(map(int, f.readline().split(" "))) for _ in range(nBuildings)]
        # antenna = [range, connection, id]
        antennas = [list(map(int, f.readline().split(" ") + [n] )) for n in range(nAntennas)]

        log(id, "Input read")

        buildings = sorted(buildings, key=lambda building: building[3], reverse=True)
        antennas = sorted(antennas, key=lambda antenna: antenna[1], reverse=True)

        log(id, "Input sorted")

        log(id, "Running")
        #[[x, y, range, connection, id],...]
        placedAntennas = []
        for b in buildings:
            if not antennas:
                break
            if notInRange(b, placedAntennas):
                placedAntennas += [[b[0], b[1]] + antennas[0]]
                del antennas[0]
        fw.write(str(len(placedAntennas))+"\n")
        for a in placedAntennas:
            fw.write(str(a[4])+" "+str(a[0])+" "+str(a[1])+" \n")

        log(id, "Finished")


inputs = ["a.in","b.in","c.in","d.in","e.in","f.in"]
threads = []
for i in range(6):
    threads += [threading.Thread(target=solve, args=[inputs[i], i])]

for t in threads:
    t.start()

for t in threads:
    t.join()
