/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.comparesorts;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.data.xy.XYSeries;

/**
 *
 * @author berat
 */
public class CompareSorts {

    public static void main(String[] args) {

        long before = 0;
        long after = 0;
        long[] sortTimes = new long[7];

        MergeSort merge = new MergeSort();

        HeapSort heap = new HeapSort();

        QuickSort quick = new QuickSort();

        SelectionSort selection = new SelectionSort();

        InsertionSort insertion = new InsertionSort();

        BubbleSort bubble = new BubbleSort();

        int[] arr1 = new int[10];
        int[] arr2 = new int[100];
        int[] arr3 = new int[1000];
        int[] arr4 = new int[10000];
        int[] arr5 = new int[50000];
        int[] arr6 = new int[100000];
        int[] arr7 = new int[500000];

        for (int i = 0; i < 10; i++) {
            arr1[i] = (int) (Math.random() * 1000);
        }

        for (int element : arr1) {
            System.out.print(element + " ");
        }

        System.out.println();

        for (int i = 0; i < 100; i++) {
            arr2[i] = (int) (Math.random() * 1000);
        }

        for (int i = 0; i < 1000; i++) {
            arr3[i] = (int) (Math.random() * 1000);
        }

        for (int i = 0; i < 10000; i++) {
            arr4[i] = (int) (Math.random() * 1000);
        }

        for (int i = 0; i < 50000; i++) {
            arr5[i] = (int) (Math.random() * 1000);
        }

        for (int i = 0; i < 100000; i++) {
            arr6[i] = (int) (Math.random() * 1000);
        }

        for (int i = 0; i < 500000; i++) {
            arr7[i] = (int) (Math.random() * 1000);
        }
        int[][] matrice = {arr1, arr2, arr3, arr4, arr5, arr6, arr7};

        Scanner scan = new Scanner(System.in);
        System.out.println("MergeSort");
        System.out.println("HeapSort");
        System.out.println("QuickSort");
        System.out.println("SelectionSort");
        System.out.println("InsertionSort");
        System.out.println("BubbleSort");
        System.out.print("Select a method to sort and see the chart: ");

        String method = scan.next();

        switch (method) {
            case "MergeSort":
                for (int i = 0; i < 7; i++) {
                    before = System.nanoTime();
                    merge.sort(matrice[i], 0, matrice[i].length - 1);
                    after = System.nanoTime();
                    sortTimes[i] = after - before;
                }
                for (int element : matrice[0]) {
                    System.out.print(element + " ");
                }
                System.out.println();
                break;

            case "HeapSort":
                for (int i = 0; i < 7; i++) {
                    before = System.nanoTime();
                    heap.sort(matrice[i]);
                    after = System.nanoTime();
                    sortTimes[i] = after - before;
                }
                for (int element : matrice[0]) {
                    System.out.print(element + " ");
                }
                System.out.println();
                break;

            case "QuickSort":
                for (int i = 0; i < 7; i++) {
                    before = System.nanoTime();
                    quick.sort(matrice[i], 0, matrice[i].length - 1);
                    after = System.nanoTime();
                    sortTimes[i] = after - before;
                }
                for (int element : matrice[0]) {
                    System.out.print(element + " ");
                }
                System.out.println();
                break;

            case "SelectionSort":
                for (int i = 0; i < 7; i++) {
                    before = System.nanoTime();
                    selection.sort(matrice[i]);
                    after = System.nanoTime();
                    sortTimes[i] = after - before;
                }
                for (int element : matrice[0]) {
                    System.out.print(element + " ");
                }
                System.out.println();
                break;

            case "InsertionSort":
                for (int i = 0; i < 7; i++) {
                    before = System.nanoTime();
                    insertion.sort(matrice[i]);
                    after = System.nanoTime();
                    sortTimes[i] = after - before;
                }
                for (int element : matrice[0]) {
                    System.out.print(element + " ");
                }
                System.out.println();
                break;

            case "BubbleSort":
                for (int i = 0; i < 7; i++) {
                    before = System.nanoTime();
                    bubble.sort(matrice[i]);
                    after = System.nanoTime();
                    sortTimes[i] = after - before;
                }
                for (int element : matrice[0]) {
                    System.out.print(element + " ");
                }
                System.out.println();
                break;

            default:

                break;
        }

        XYSeries xySeries = new XYSeries(method + " Chart");

        xySeries.add(sortTimes[0], 10);
        xySeries.add(sortTimes[1], 100);
        xySeries.add(sortTimes[2], 1000);
        xySeries.add(sortTimes[3], 10000);
        xySeries.add(sortTimes[4], 50000);
        xySeries.add(sortTimes[5], 100000);
        xySeries.add(sortTimes[6], 500000);

        XYSeriesCollection dataSet = new XYSeriesCollection();
        dataSet.addSeries(xySeries);

        JFreeChart chart = ChartFactory.createXYLineChart("Grafik", "Time (Nanoseconds)", "Array Size", dataSet, PlotOrientation.HORIZONTAL, true, true, false);

        try {
            ChartUtilities.saveChartAsJPEG(new File(method + ".JPEG"), chart, 800, 600);
        } catch (IOException ex) {
            Logger.getLogger(CompareSorts.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    static class MergeSort {

        void merge(int arr[], int l, int m, int r) {
            int n1 = m - l + 1;
            int n2 = r - m;

            int L[] = new int[n1];
            int R[] = new int[n2];

            for (int i = 0; i < n1; ++i) {
                L[i] = arr[l + i];
            }
            for (int j = 0; j < n2; ++j) {
                R[j] = arr[m + 1 + j];
            }

            int i = 0, j = 0;

            int k = l;
            while (i < n1 && j < n2) {
                if (L[i] <= R[j]) {
                    arr[k] = L[i];
                    i++;
                } else {
                    arr[k] = R[j];
                    j++;
                }
                k++;
            }

            while (i < n1) {
                arr[k] = L[i];
                i++;
                k++;
            }

            while (j < n2) {
                arr[k] = R[j];
                j++;
                k++;
            }
        }

        void sort(int arr[], int l, int r) {
            if (l < r) {
                int m = l + (r - l) / 2;

                sort(arr, l, m);
                sort(arr, m + 1, r);

                merge(arr, l, m, r);
            }
        }

        static void printArray(int arr[]) {
            int n = arr.length;
            for (int i = 0; i < n; ++i) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }
    }

    static class HeapSort {

        void sort(int arr[]) {
            int n = arr.length;

            for (int i = n / 2 - 1; i >= 0; i--) {
                heapify(arr, n, i);
            }

            for (int i = n - 1; i > 0; i--) {

                int temp = arr[0];
                arr[0] = arr[i];
                arr[i] = temp;

                heapify(arr, i, 0);
            }
        }

        void heapify(int arr[], int n, int i) {
            int largest = i;
            int l = 2 * i + 1;
            int r = 2 * i + 2;

            if (l < n && arr[l] > arr[largest]) {
                largest = l;
            }

            if (r < n && arr[r] > arr[largest]) {
                largest = r;
            }

            if (largest != i) {
                int swap = arr[i];
                arr[i] = arr[largest];
                arr[largest] = swap;

                heapify(arr, n, largest);
            }
        }

        static void printArray(int arr[]) {
            int n = arr.length;
            for (int i = 0; i < n; ++i) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }
    }

    static class QuickSort {

        static void swap(int[] arr, int i, int j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

        int partition(int[] arr, int low, int high) {

            int pivot = arr[high];

            int i = (low - 1);

            for (int j = low; j <= high - 1; j++) {

                if (arr[j] < pivot) {

                    i++;
                    swap(arr, i, j);
                }
            }
            swap(arr, i + 1, high);
            return (i + 1);
        }

        void sort(int[] arr, int low, int high) {
            if (low < high) {

                int pi = partition(arr, low, high);

                sort(arr, low, pi - 1);
                sort(arr, pi + 1, high);
            }
        }

        static void printArray(int[] arr, int size) {
            for (int i = 0; i < size; i++) {
                System.out.print(arr[i] + " ");
            }

            System.out.println();
        }
    }

    static class SelectionSort {

        void sort(int arr[]) {
            int n = arr.length;

            for (int i = 0; i < n - 1; i++) {
                int min_idx = i;
                for (int j = i + 1; j < n; j++) {
                    if (arr[j] < arr[min_idx]) {
                        min_idx = j;
                    }
                }

                int temp = arr[min_idx];
                arr[min_idx] = arr[i];
                arr[i] = temp;
            }
        }

        void printArray(int arr[]) {
            int n = arr.length;
            for (int i = 0; i < n; ++i) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }
    }

    static class InsertionSort {

        void sort(int arr[]) {
            int n = arr.length;
            for (int i = 1; i < n; ++i) {
                int key = arr[i];
                int j = i - 1;

                while (j >= 0 && arr[j] > key) {
                    arr[j + 1] = arr[j];
                    j = j - 1;
                }
                arr[j + 1] = key;
            }
        }

        static void printArray(int arr[]) {
            int n = arr.length;
            for (int i = 0; i < n; ++i) {
                System.out.print(arr[i] + " ");
            }

            System.out.println();
        }
    }

    static class BubbleSort {

        void sort(int arr[]) {
            int n = arr.length;
            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n - i - 1; j++) {
                    if (arr[j] > arr[j + 1]) {
                        int temp = arr[j];
                        arr[j] = arr[j + 1];
                        arr[j + 1] = temp;
                    }
                }
            }
        }

        void printArray(int arr[]) {
            int n = arr.length;
            for (int i = 0; i < n; ++i) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }
    }

}
