if $(nasm -felf64 wordcount.asm && ld wordcount.o -o wordcount); then
    ./wordcount
fi
