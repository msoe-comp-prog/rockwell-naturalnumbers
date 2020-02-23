//
// Created by johnsonn on 02/21/2020.
//

#include <iostream>
#include <string>
#include <fstream>
#include <vector>
#include <map>

typedef uint_fast64_t ull;

ull numDivisors(ull num) {
    std::map<ull, int> factors;

    ull cpy = num;
    while ((cpy & 1u) == 0) {
        if (factors.find(2) != factors.end()) {
            int place = factors.find(2)->second + 1;
            factors.erase(2);
            factors.emplace(2, place);
        } else {
            factors.emplace(2, 1);
        }
        cpy = cpy >> 1u;
    }

    for (size_t i = 3; cpy != 1; i += 2) {
        while (cpy % i == 0) {
            if (factors.find(i) != factors.end()) {
                int place = factors.find(i)->second + 1;
                factors.erase(i);
                factors.emplace(i, place);
            } else {
                factors.emplace(i, 1);
            }
            cpy /= i;
        }
    }

    ull tot = 1;
    for (auto ent : factors) {
        tot *= (ent.second + 1);
    }

    return tot;
}

int main() {
    std::ifstream file("natnums.txt");

    std::string line;

    std::vector<ull> placeToVals;
    ull maxDivs = 1;

    placeToVals.push_back(1);

    while (std::getline(file, line)) {
        ull num = std::stol(line);
        while (maxDivs < num) {
            ull n = placeToVals.at(placeToVals.size() - 1) + placeToVals.size() + 1;
            ull divs = numDivisors(n);
            if (divs > maxDivs) {
                maxDivs = divs;
            }
            placeToVals.push_back(n);
        }
        std::cout << placeToVals.size() << std::endl;
    }
    return 0;
}
