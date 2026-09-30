import os
import sys
import xml.etree.ElementTree as ET
from pathlib import Path


def load_results(directory):
    results = []
    for file in sorted(Path(directory).glob("TEST-*.xml")):
        for case in ET.parse(file).getroot().iter("testcase"):
            problem = case.find("failure")
            if problem is None:
                problem = case.find("error")
            if problem is not None:
                status = "failed"
            elif case.find("skipped") is not None:
                status = "skipped"
            else:
                status = "passed"
            results.append({
                "name": case.get("name", ""),
                "class": case.get("classname", "").rsplit(".", 1)[-1],
                "time": float(case.get("time") or 0),
                "status": status,
                "message": error_message(problem),
            })
    return results


def error_message(problem):
    if problem is None:
        return ""
    text = problem.get("message") or problem.get("type") or ""
    first_line = text.strip().splitlines()[0] if text.strip() else ""
    return first_line[:200]


def cell(text):
    return text.replace("|", "\\|").replace("<", "&lt;").replace(">", "&gt;")


def seconds(value):
    return f"{value:.1f}s"


def render(results, duration):
    passed = [r for r in results if r["status"] == "passed"]
    failed = [r for r in results if r["status"] == "failed"]
    skipped = [r for r in results if r["status"] == "skipped"]

    icon = "❌" if failed else "✅"
    header = (f"## {icon} Test results — {len(passed)} passed, {len(failed)} failed, "
              f"{len(skipped)} skipped ({len(results)} total)")
    if duration:
        header += f" · {seconds(float(duration))}"
    lines = [header, ""]

    if not results:
        lines.append("No test results found.")
        return "\n".join(lines)

    if failed:
        lines += ["### Failed", "", "| Test | Class | Time | Error |", "|------|-------|------|-------|"]
        for r in failed:
            lines.append(f"| {cell(r['name'])} | {cell(r['class'])} | {seconds(r['time'])} | {cell(r['message'])} |")
        lines.append("")

    if skipped:
        lines += ["### Skipped", "", "| Test | Class |", "|------|-------|"]
        for r in skipped:
            lines.append(f"| {cell(r['name'])} | {cell(r['class'])} |")
        lines.append("")

    if passed:
        lines += ["### Passed", "", "| Test | Class | Time |", "|------|-------|------|"]
        for r in sorted(passed, key=lambda r: r["time"], reverse=True):
            lines.append(f"| {cell(r['name'])} | {cell(r['class'])} | {seconds(r['time'])} |")
        lines.append("")

    return "\n".join(lines)


if __name__ == "__main__":
    directory = sys.argv[1] if len(sys.argv) > 1 else "target/surefire-reports"
    print(render(load_results(directory), os.environ.get("TEST_DURATION")))
