import os
import re

def count_java_lines(file_path):
    with open(file_path, 'r', encoding='utf-8', errors='ignore') as file:
        lines = file.readlines()
    
    code_lines = 0
    in_block_comment = False
    
    for line in lines:
        stripped = line.strip()
        
        if in_block_comment:
            if '*/' in stripped:
                in_block_comment = False
            continue
        
        if stripped.startswith('/*'):
            in_block_comment = True
            continue
        
        if stripped.startswith('//') or not stripped:
            continue
        
        code_lines += 1
    
    return code_lines

def process_directory(directory):
    results = []
    for root, _, files in os.walk(directory):
        for file in files:
            if file.endswith('.java'):
                file_path = os.path.join(root, file)
                lines_count = count_java_lines(file_path)
                results.append((file, lines_count))
    
    for file, lines_count in sorted(results):
        print(f'{file}: {lines_count} líneas de código')

if __name__ == "__main__":
    folder_path = input("Ingrese la ruta de la carpeta: ").strip()
    if os.path.isdir(folder_path):
        process_directory(folder_path)
    else:
        print("La ruta proporcionada no es válida.")
